# exercises/advanced/suffix_automaton/suffix_automaton.py
from collections import defaultdict, deque
from typing import Dict, List, Tuple

class SuffixAutomaton:
    """
    Suffix Automaton (SAM) implementation with utilities:
      - add_string(s): build SAM for s
      - contains(t): check if t is a substring of s
      - count_distinct_substrings(): count distinct substrings of s
      - longest_common_substring(t): length and one example of LCS with t
      - occurrences(t): number of occurrences of t in s (requires endpos propagation)
    Complexity: build O(n), queries O(|t|) generally.
    """

    class State:
        __slots__ = ("len", "link", "next", "occ")
        def __init__(self):
            self.len = 0
            self.link = -1
            self.next: Dict[str,int] = {}
            self.occ = 0  # for occurrence counting (endpos size)

    def __init__(self):
        self.states: List[SuffixAutomaton.State] = []
        self.last = 0
        self._init_sam()

    def _init_sam(self):
        self.states = [SuffixAutomaton.State()]
        self.states[0].len = 0
        self.states[0].link = -1
        self.last = 0

    def sa_extend(self, c: str):
        """Extend SAM by character c (single char string)."""
        p = self.last
        cur = len(self.states)
        self.states.append(SuffixAutomaton.State())
        self.states[cur].len = self.states[p].len + 1
        self.states[cur].occ = 1  # this state corresponds to a new end position

        while p != -1 and c not in self.states[p].next:
            self.states[p].next[c] = cur
            p = self.states[p].link

        if p == -1:
            self.states[cur].link = 0
        else:
            q = self.states[p].next[c]
            if self.states[p].len + 1 == self.states[q].len:
                self.states[cur].link = q
            else:
                # clone
                clone = len(self.states)
                self.states.append(SuffixAutomaton.State())
                self.states[clone].len = self.states[p].len + 1
                self.states[clone].next = self.states[q].next.copy()
                self.states[clone].link = self.states[q].link
                # occ for clone stays 0 (we'll propagate later)
                while p != -1 and self.states[p].next.get(c) == q:
                    self.states[p].next[c] = clone
                    p = self.states[p].link
                self.states[q].link = self.states[cur].link = clone

        self.last = cur

    def build(self, s: str):
        """Build SAM for string s."""
        self._init_sam()
        for ch in s:
            self.sa_extend(ch)
        # After building, we can propagate occurrence counts if desired
        self._propagate_occurrences()

    def _propagate_occurrences(self):
        """Propagate endpos counts from longer states to linked states."""
        # Bucket states by length (counting sort approach)
        max_len = max(state.len for state in self.states) if self.states else 0
        buckets = [0] * (max_len + 1)
        for st in self.states:
            buckets[st.len] += 1
        for i in range(1, len(buckets)):
            buckets[i] += buckets[i - 1]
        order = [None] * len(self.states)
        for i in range(len(self.states) - 1, -1, -1):
            st = self.states[i]
            buckets[st.len] -= 1
            order[buckets[st.len]] = i
        # traverse states from longest to shortest
        for idx in reversed(order):
            st = self.states[idx]
            if st.link != -1:
                self.states[st.link].occ += st.occ

    def contains(self, t: str) -> bool:
        """Return True if t is a substring of the built string."""
        cur = 0
        for ch in t:
            if ch not in self.states[cur].next:
                return False
            cur = self.states[cur].next[ch]
        return True

    def count_distinct_substrings(self) -> int:
        """Number of distinct substrings of the original string."""
        # sum over states: len[state] - len[link[state]]
        total = 0
        for i, st in enumerate(self.states):
            if st.link != -1:
                total += st.len - self.states[st.link].len
            else:
                total += st.len  # for root, link == -1 contributes len (but root len is 0)
        # but root len is zero so above still works
        # standard formula: sum_{v} (len[v] - len[link[v]])
        total = sum((st.len - (self.states[st.link].len if st.link != -1 else 0)) for st in self.states)
        return total

    def longest_common_substring(self, t: str) -> Tuple[int, str]:
        """
        Find the length and a substring that is the LCS between the built string and t.
        Returns (length, substring_example).
        """
        v = 0
        l = 0
        best = 0
        best_pos = 0  # position in t where best ends
        for i, ch in enumerate(t):
            # walk with transitions; if not possible, follow links
            while v != 0 and ch not in self.states[v].next:
                v = self.states[v].link
                l = self.states[v].len if v != -1 else 0
            if ch in self.states[v].next:
                v = self.states[v].next[ch]
                l += 1
            else:
                v = 0
                l = 0
            if l > best:
                best = l
                best_pos = i
        if best == 0:
            return 0, ""
        return best, t[best_pos - best + 1: best_pos + 1]

    def occurrences(self, t: str) -> int:
        """
        Return number of occurrences of t in the built string.
        Requires that _propagate_occurrences was called after build (we call it automatically).
        """
        cur = 0
        for ch in t:
            if ch not in self.states[cur].next:
                return 0
            cur = self.states[cur].next[ch]
        return self.states[cur].occ

# A minimal CLI-style helper (importable)
def build_from_string(s: str) -> SuffixAutomaton:
    sam = SuffixAutomaton()
    sam.build(s)
    return sam
