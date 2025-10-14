#!/usr/bin/env python3
"""
md_link_checker.py

Simple Markdown link checker for repositories — find and report dead links in .md files.

Usage:
    python md_link_checker.py               # scan current directory recursively
    python md_link_checker.py path/to/dir   # scan specified directory
    python md_link_checker.py --timeout 5 --workers 20 README.md docs/

Features:
- Finds links in Markdown-style [text](url) and bare http/https urls.
- Checks URLs using HTTP HEAD (falls back to GET if HEAD is not allowed).
- Concurrent checking with ThreadPoolExecutor (no external dependencies).
- Outputs a summary and returns exit code 2 if any dead/unreachable links found,
  exit code 0 if all links OK, 1 on internal error.

Good for Hacktoberfest: small useful utility, easy-to-review single-file contribution.

Author: Generated for Hacktoberfest 2025 contribution.
License: MIT
"""
from __future__ import annotations

import argparse
import concurrent.futures
import logging
import os
import re
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from typing import Dict, List, Optional, Set, Tuple

# Regex patterns
MD_LINK_RE = re.compile(r'\[([^\]]+)\]\((https?://[^\s\)]+)\)')
URL_RE = re.compile(r'(?<!\()(?P<url>https?://[^\s\)]+)')

# Exit codes:
EXIT_OK = 0
EXIT_INTERNAL_ERROR = 1
EXIT_BROKEN_LINKS = 2

# Defaults
DEFAULT_TIMEOUT = 10.0
DEFAULT_WORKERS = 10

logging.basicConfig(level=logging.INFO, format='%(levelname)s: %(message)s')
logger = logging.getLogger("md-link-checker")


def find_markdown_files(paths: List[str]) -> List[str]:
    """Recursively find .md files in given paths (files allowed)."""
    md_files: List[str] = []
    for p in paths or ['.']:
        if os.path.isfile(p) and p.lower().endswith('.md'):
            md_files.append(os.path.abspath(p))
        elif os.path.isdir(p):
            for root, _, files in os.walk(p):
                for f in files:
                    if f.lower().endswith('.md'):
                        md_files.append(os.path.join(root, f))
        else:
            logger.warning("Path not found or not markdown: %s", p)
    return sorted(md_files)


def extract_links_from_text(text: str) -> Set[str]:
    """Extract http(s) links from markdown text (markdown links and bare URLs)."""
    urls: Set[str] = set()
    for m in MD_LINK_RE.finditer(text):
        urls.add(m.group(2).rstrip(').,'))
    for m in URL_RE.finditer(text):
        urls.add(m.group('url').rstrip(').,'))
    return urls


def extract_links_from_file(path: str) -> Set[str]:
    """Read file and extract links. Returns set of unique urls."""
    try:
        with open(path, 'r', encoding='utf-8') as fh:
            content = fh.read()
    except Exception as e:
        logger.error("Failed to read %s: %s", path, e)
        return set()
    return extract_links_from_text(content)


def check_url(url: str, timeout: float = DEFAULT_TIMEOUT) -> Tuple[str, bool, Optional[str]]:
    """
    Check a single URL.
    Returns (url, ok, message). ok == True when status is 200-399.
    """
    # Normalize
    parsed = urllib.parse.urlsplit(url)
    if not parsed.scheme:
        return url, False, "no-scheme"

    req = urllib.request.Request(url, method='HEAD', headers={'User-Agent': 'md-link-checker/1.0'})
    try:
        with urllib.request.urlopen(req, timeout=timeout) as resp:
            code = resp.getcode()
            if 200 <= code < 400:
                return url, True, f"HTTP {code}"
            return url, False, f"HTTP {code}"
    except urllib.error.HTTPError as he:
        # If HEAD not allowed -> try GET
        if he.code in (405, 501):
            try:
                req2 = urllib.request.Request(url, method='GET', headers={'User-Agent': 'md-link-checker/1.0'})
                with urllib.request.urlopen(req2, timeout=timeout) as resp2:
                    code2 = resp2.getcode()
                    if 200 <= code2 < 400:
                        return url, True, f"HTTP {code2}"
                    return url, False, f"HTTP {code2}"
            except Exception as e2:
                return url, False, f"GET failed: {e2}"
        return url, False, f"HTTPError {he.code}"
    except urllib.error.URLError as ue:
        return url, False, f"URLError: {ue.reason}"
    except Exception as e:
        return url, False, f"Error: {e}"


def check_urls_concurrent(urls: List[str], timeout: float = DEFAULT_TIMEOUT, workers: int = DEFAULT_WORKERS) -> Dict[str, Tuple[bool, Optional[str]]]:
    """
    Check multiple URLs concurrently. Returns dict mapping url -> (ok, message)
    """
    results: Dict[str, Tuple[bool, Optional[str]]] = {}
    with concurrent.futures.ThreadPoolExecutor(max_workers=workers) as ex:
        future_to_url = {ex.submit(check_url, u, timeout): u for u in urls}
        for fut in concurrent.futures.as_completed(future_to_url):
            u = future_to_url[fut]
            try:
                _, ok, msg = fut.result()
                results[u] = (ok, msg)
            except Exception as e:
                results[u] = (False, f"Exception: {e}")
    return results


def main(argv: Optional[List[str]] = None) -> int:
    p = argparse.ArgumentParser(description="Simple Markdown link checker (no deps).")
    p.add_argument('paths', nargs='*', help='Files or directories to scan (default: .)')
    p.add_argument('--timeout', '-t', type=float, default=DEFAULT_TIMEOUT, help=f'HTTP timeout seconds (default {DEFAULT_TIMEOUT})')
    p.add_argument('--workers', '-w', type=int, default=DEFAULT_WORKERS, help=f'Concurrent workers (default {DEFAULT_WORKERS})')
    p.add_argument('--verbose', '-v', action='store_true', help='Verbose output')
    p.add_argument('--show-ok', action='store_true', help='Show OK links as well as broken ones')
    args = p.parse_args(argv)

    if args.verbose:
        logger.setLevel(logging.DEBUG)

    start = time.time()
    md_files = find_markdown_files(args.paths or ['.'])
    if not md_files:
        logger.info("No markdown files found.")
        return EXIT_OK

    logger.info("Found %d markdown files. Scanning for links...", len(md_files))
    all_urls: Set[str] = set()
    file_map: Dict[str, Set[str]] = {}
    for md in md_files:
        urls = extract_links_from_file(md)
        if urls:
            file_map[md] = urls
            all_urls.update(urls)

    if not all_urls:
        logger.info("No links found in markdown files.")
        return EXIT_OK

    logger.info("Checking %d unique URLs with %d workers (timeout=%ss)...", len(all_urls), args.workers, args.timeout)
    results = check_urls_concurrent(sorted(all_urls), timeout=args.timeout, workers=args.workers)

    broken: List[Tuple[str, str]] = []
    ok_count = 0
    for url, (ok, msg) in results.items():
        if ok:
            ok_count += 1
            if args.show_ok:
                logger.info("OK: %s -- %s", url, msg)
        else:
            broken.append((url, msg))
            logger.warning("BROKEN: %s -- %s", url, msg)

    # Produce per-file summary
    logger.info("---- SUMMARY ----")
    logger.info("Files scanned: %d", len(md_files))
    logger.info("Links found: %d (unique %d)", sum(len(s) for s in file_map.values()), len(all_urls))
    logger.info("OK: %d | Broken: %d", ok_count, len(broken))
    elapsed = time.time() - start
    logger.info("Elapsed time: %.2fs", elapsed)

    if broken:
        # show which files contain broken links
        logger.info("Broken links appear in these files:")
        for f, urls in file_map.items():
            hit = [u for u, _ in broken if u in urls]
            if hit:
                logger.info(" - %s: %d broken link(s)", f, len(hit))
                for u in hit:
                    # find message
                    msg = results[u][1]
                    logger.debug("   %s -> %s", u, msg)
        return EXIT_BROKEN_LINKS

    return EXIT_OK


if __name__ == '__main__':
    try:
        sys.exit(main())
    except KeyboardInterrupt:
        logger.error("Aborted by user")
        sys.exit(EXIT_INTERNAL_ERROR)
