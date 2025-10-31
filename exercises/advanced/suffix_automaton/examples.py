# exercises/advanced/suffix_automaton/examples.py
from suffix_automaton import build_from_string

def demo():
    s = "abracadabra"
    sam = build_from_string(s)
    print("Original:", s)
    print("Contains 'cada'?", sam.contains("cada"))
    print("Contains 'xyz'?", sam.contains("xyz"))
    print("Distinct substrings:", sam.count_distinct_substrings())
    l, substr = sam.longest_common_substring("cadabra")
    print("LCS with 'cadabra':", l, substr)
    print("Occurrences of 'abra':", sam.occurrences("abra"))

if __name__ == "__main__":
    demo()
