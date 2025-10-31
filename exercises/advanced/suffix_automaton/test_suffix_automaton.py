# exercises/advanced/suffix_automaton/test_suffix_automaton.py
import pytest
from suffix_automaton import build_from_string

def test_contains_and_occurrences():
    s = "ababab"
    sam = build_from_string(s)
    assert sam.contains("aba")
    assert sam.contains("bab")
    assert not sam.contains("baa")
    # occurrences: "aba" appears twice in "ababab" (positions 0 and 2)
    assert sam.occurrences("aba") == 2
    assert sam.occurrences("ab") == 3

def test_distinct_substrings_small():
    s = "aaa"
    sam = build_from_string(s)
    # substrings: "a", "aa", "aaa" => 3 distinct
    assert sam.count_distinct_substrings() == 3

def test_lcs_example():
    s = "abcdxyz"
    t = "xyzabcd"
    sam = build_from_string(s)
    length, substr = sam.longest_common_substring(t)
    assert length == 4
    # substring can be "abcd" or "xyz" depending — check length and membership
    assert len(substr) == length
    assert substr in s and substr in t

def test_no_common():
    s = "abc"
    t = "zzz"
    sam = build_from_string(s)
    length, substr = sam.longest_common_substring(t)
    assert length == 0
    assert substr == ""
