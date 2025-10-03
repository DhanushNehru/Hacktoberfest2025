# Contributing to Hacktoberfest2025

Thanks for your interest in contributing! This repository collects many small example programs across languages — your contributions make it better and more accessible for everyone.

This document explains how to report issues, propose changes, and submit pull requests so maintainers can review and merge quickly.

## ✅ Good first contributions
Look for issues labeled `good first issue`. Good starter tasks include:
- Fixing typos or README documentation.
- Adding or improving examples for existing scripts.
- Implementing small `TODO` stubs in `Java/Recursion` examples.
- Adding simple tests for single-file Python scripts.

## How to report issues
- Search existing issues first — your issue may already be reported.
- When opening a new issue include:
  - A clear title.
  - Steps to reproduce (if applicable).
  - Expected vs actual behavior.
  - Suggested fix (optional).

## How to contribute code (Fork & PR workflow)
1. Fork the repository on GitHub.
2. Clone your fork:
```powershell
git clone https://github.com/<your-username>/Hacktoberfest2025.git
cd Hacktoberfest2025
```
3. Create a descriptive branch:
```powershell
git checkout -b fix/readme-typo
# or
git checkout -b feat/add-contributing-md
```
4. Make your changes. Keep changes small and focused.
5. Stage and commit your changes with a clear message:
```powershell
git add .
git commit -m "chore: add CONTRIBUTING.md"
git push -u origin your-branch-name
```
6. Open a Pull Request against `main`. Reference the issue (if any) in the PR description.

### Commit message examples
- `fix: correct typo in README`
- `feat(java): implement FirstRepeatedCharacter example`
- `chore: add CONTRIBUTING.md`

## Pull request checklist
- [ ] The PR targets the `main` branch.
- [ ] Changes are small, documented, and tested where applicable.
- [ ] Documentation (README/CONTRIBUTING) updated if necessary.
- [ ] Commit messages are clear and follow the examples above.
- [ ] Link the issue this PR closes (if applicable).

## Running tests and tools
This repo contains many languages. If your change touches one language, run the normal local toolchain for that language before opening the PR:
- Python: create a venv and run `pytest` (if tests exist) or run the script with `python`.
- Java: compile with `javac` and run with `java`.
- C/C++: compile with `g++`/`clang++`.

If you add tests, include instructions to run them in the PR description.

## Code style
- Keep changes small and focused.
- Follow the idiomatic style for the language you change (PEP8 for Python, standard Java conventions, etc.).

## Labels and triage (for maintainers)
- `good first issue` — beginner-friendly tasks.
- `help wanted` — contributions welcome.
- `documentation` — docs-only changes.
- `license` — license/legal tasks.
- `chore` — non-functional maintenance.

## Code of Conduct
Be respectful and collaborative. If you want, we can add a `CODE_OF_CONDUCT.md` later.

---

If you'd like, I can now create a branch and prepare a PR body for this file so you can copy-paste and open the PR on GitHub. Tell me if you want that or if you'd prefer to push from your machine.
