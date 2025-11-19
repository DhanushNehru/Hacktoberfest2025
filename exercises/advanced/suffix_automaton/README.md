# Suffix Automaton (SAM)

This exercise implements a Suffix Automaton and small utilities.

## What this provides
- `suffix_automaton.py`: implementation and helper functions.
- `examples.py`: run `python examples.py` to see sample outputs.
- `test_suffix_automaton.py`: unit tests (pytest).

## Algorithms & Problems solvable
- Check if a string `t` is a substring of `s`
- Count distinct substrings of `s` (linear time)
- Longest common substring between `s` and `t`
- Count number of occurrences of `t` in `s` (using endpos propagation)

## Complexity
- Construction: O(n) where n = len(s)
- Queries (contains/occurrences/LCS): O(|t|)

## How to run
```bash
python examples.py
pytest -q
