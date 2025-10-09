#!/usr/bin/env python3
"""
Author : Bit0x1er
HTTP Header Security Analyzer (Upgraded Edition)
- Single URL or newline-separated file of URLs
- Concurrent scanning, JSON output, suggestions
- Shows missing headers clearly in results!
Dependencies: requests
Usage examples:
  python3 http_header_analyzer.py https://example.com
  python3 http_header_analyzer.py targets.txt --threads 20 --output results.json -v
"""

import argparse
import json
import sys
import time
from concurrent.futures import ThreadPoolExecutor, as_completed
from urllib.parse import urlparse
import requests


class C:
    BLUE = "\033[94m"
    GREEN = "\033[92m"
    YELLOW = "\033[93m"
    RED = "\033[91m"
    MAGENTA = "\033[95m"
    CYAN = "\033[96m"
    BOLD = "\033[1m"
    END = "\033[0m"

def colored(s, color):
    return f"{color}{s}{C.END}"


HEADER_CHECKS = [
    {"name": "Strict-Transport-Security","keys": ["strict-transport-security"],
     "good_if": lambda v: ("max-age" in v.lower() and int(parsed_max_age(v)) >= 31536000) if v else False,
     "suggestion": "Set HSTS: 'Strict-Transport-Security: max-age=31536000; includeSubDomains; preload'"},
    {"name": "Content-Security-Policy","keys": ["content-security-policy"],
     "good_if": lambda v: bool(v) and ("'unsafe-inline'" not in v and "'unsafe-eval'" not in v),
     "suggestion": "Add CSP to prevent XSS. Avoid 'unsafe-inline'/'unsafe-eval'."},
    {"name": "X-Frame-Options","keys": ["x-frame-options"],
     "good_if": lambda v: bool(v) and v.lower() in ("deny", "sameorigin"),
     "suggestion": "Set X-Frame-Options: DENY or SAMEORIGIN."},
    {"name": "X-Content-Type-Options","keys": ["x-content-type-options"],
     "good_if": lambda v: bool(v) and v.lower() == "nosniff",
     "suggestion": "Add X-Content-Type-Options: nosniff."},
    {"name": "Referrer-Policy","keys": ["referrer-policy"],
     "good_if": lambda v: bool(v) and v.lower() in ("no-referrer", "strict-origin-when-cross-origin", "same-origin"),
     "suggestion": "Use Referrer-Policy: 'no-referrer' or 'strict-origin-when-cross-origin'."},
    {"name": "Permissions-Policy","keys": ["permissions-policy", "feature-policy"],
     "good_if": lambda v: bool(v),
     "suggestion": "Add Permissions-Policy to limit powerful APIs."},
    {"name": "Cross-Origin-Opener-Policy","keys": ["cross-origin-opener-policy"],
     "good_if": lambda v: bool(v) and v.lower().startswith("same-origin"),
     "suggestion": "Set Cross-Origin-Opener-Policy: same-origin."},
    {"name": "Cross-Origin-Embedder-Policy","keys": ["cross-origin-embedder-policy"],
     "good_if": lambda v: bool(v) and v.lower().startswith("require-corp"),
     "suggestion": "Set Cross-Origin-Embedder-Policy: require-corp."},
    {"name": "Expect-CT","keys": ["expect-ct"],
     "good_if": lambda v: bool(v),
     "suggestion": "Consider Expect-CT for certificate transparency."},
]

def parsed_max_age(hsts_value):
    try:
        parts = hsts_value.split(";")
        for p in parts:
            if "max-age" in p.lower():
                return ''.join(ch for ch in p if ch.isdigit())
    except Exception:
        return "0"
    return "0"

# --- scoring logic ---
def score_headers(headers):
    score = 0
    maxscore = len(HEADER_CHECKS)
    details = []
    lower_headers = {k.lower(): v for k, v in headers.items()}
    for chk in HEADER_CHECKS:
        found, value = None, None
        for key in chk["keys"]:
            if key in lower_headers:
                found = True
                value = lower_headers[key]
                break
        ok = chk["good_if"](value) if found else False
        details.append({
            "name": chk["name"],
            "present": bool(found),
            "value": value,
            "ok": bool(ok),
            "suggestion": None if ok else chk["suggestion"]
        })
        if ok:
            score += 1
    return {"score": score, "max": maxscore, "details": details}


def normalize_url(u):
    u = u.strip()
    if not u:
        return None
    parsed = urlparse(u)
    if not parsed.scheme:
        u = "https://" + u
    return u

def probe_url(url, timeout=6, retries=1, headers=None):
    headers = headers or {}
    session = requests.Session()
    session.headers.update(headers)
    for attempt in range(1, retries + 1):
        try:
            resp = session.head(url, allow_redirects=True, timeout=timeout, verify=True)
            if resp.status_code in (405, 501):
                resp = session.get(url, allow_redirects=True, timeout=timeout, verify=True)
            return {"status": resp.status_code, "headers": dict(resp.headers)}
        except (requests.exceptions.SSLError, requests.exceptions.ConnectionError, requests.exceptions.Timeout):
            if attempt == retries:
                return {"error": "network_error"}
            time.sleep(0.5)
        except Exception as e:
            return {"error": "unknown_error", "msg": str(e)}
    return {"error": "no_response"}


def analyze_target(target, timeout, retries, user_agent, verbose):
    url = normalize_url(target)
    if not url:
        return {"target": target, "error": "invalid_target"}
    headers = {"User-Agent": user_agent}
    result = {"target": url}
    probe = probe_url(url, timeout=timeout, retries=retries, headers=headers)
    if "error" in probe:
        result.update({"error": probe["error"]})
        return result

    result["http_status"] = probe.get("status")
    result["headers"] = probe.get("headers", {})
    score = score_headers(result["headers"])
    result["score"] = score


    missing = [d["name"] for d in score["details"] if not d["ok"]]
    result["missing_headers"] = missing


    good, total = score["score"], score["max"]
    result["summary"] = f"{good}/{total} recommended headers present"
    if verbose:
        print(colored(f"[{url}] HTTP {result.get('http_status')}", C.CYAN))
        for d in score["details"]:
            name, ok, present = d["name"], d["ok"], d["present"]
            if ok:
                print(colored(f"  ✓ {name} (OK)", C.GREEN))
            elif present:
                print(colored(f"  ! {name} present but weak -> {d['suggestion']}", C.YELLOW))
            else:
                print(colored(f"  ✗ {name} missing -> {d['suggestion']}", C.RED))
    return result


def load_targets(arg):
    try:
        with open(arg, "r") as f:
            return [l.strip() for l in f if l.strip()]
    except FileNotFoundError:
        return [arg]


def main():
    parser = argparse.ArgumentParser(description="HTTP Header Security Analyzer — upgraded with missing header visibility")
    parser.add_argument("target", help="single URL or file with URLs")
    parser.add_argument("--threads", "-t", type=int, default=10, help="number of concurrent workers")
    parser.add_argument("--timeout", type=int, default=6, help="timeout seconds")
    parser.add_argument("--retries", type=int, default=1, help="network retry count")
    parser.add_argument("--user-agent", default="HeaderAnalyzer/2.0", help="custom User-Agent")
    parser.add_argument("--output", "-o", help="write JSON results")
    parser.add_argument("--verbose", "-v", action="store_true", help="verbose mode")
    parser.add_argument("--silent", action="store_true", help="suppress terminal output")
    args = parser.parse_args()

    targets = load_targets(args.target)
    if not targets:
        print("No targets found.")
        sys.exit(1)

    results = []
    if not args.silent:
        print(colored(f"Scanning {len(targets)} target(s) with {args.threads} threads...", C.MAGENTA))

    with ThreadPoolExecutor(max_workers=max(1, args.threads)) as exe:
        futures = {exe.submit(analyze_target, t, args.timeout, args.retries, args.user_agent, args.verbose): t for t in targets}
        for fut in as_completed(futures):
            try:
                res = fut.result()
            except Exception as e:
                res = {"target": futures[fut], "error": f"exception:{e}"}
            results.append(res)

    if not args.silent and not args.verbose:
        for r in results:
            if "error" in r:
                print(colored(f"{r['target']} -> ERROR: {r['error']}", C.RED))
            else:
                missing = r.get("missing_headers", [])
                miss_txt = ", ".join(missing) if missing else "None"
                print(colored(f"{r['target']} -> {r.get('summary')}, HTTP {r.get('http_status')}; Missing: {miss_txt}", C.CYAN))

    if args.output:
        try:
            with open(args.output, "w") as f:
                json.dump(results, f, indent=2)
            if not args.silent:
                print(colored(f"JSON results written to {args.output}", C.GREEN))
        except Exception as e:
            print(colored(f"Failed writing output file: {e}", C.RED))
            sys.exit(2)

if __name__ == "__main__":
    main()
