// subset_sum_bruteforce.cpp
#include <bits/stdc++.h>
using namespace std;

/*
  Brute-force subset sum using bitmasks.
  Time complexity: O(2^n * n)
  Example: find which subset sums to target (prints first found subset).
*/
bool subset_sum_bruteforce(const vector<int>& a, int target, vector<int>& chosen) {
    int n = (int)a.size();
    long long total = 1LL << n;
    for (long long mask = 0; mask < total; ++mask) {
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if (mask & (1LL << i)) sum += a[i];
        }
        if (sum == target) {
            chosen.clear();
            for (int i = 0; i < n; ++i) if (mask & (1LL << i)) chosen.push_back(a[i]);
            return true;
        }
    }
    return false;
}

int main() {
    vector<int> arr = {3, 34, 4, 12, 5, 2};
    int target = 9;
    vector<int> ans;
    if (subset_sum_bruteforce(arr, target, ans)) {
        cout << "Subset found: ";
        for (int x : ans) cout << x << ' ';
        cout << '\n';
    } else {
        cout << "No subset sums to " << target << '\n';
    }
    return 0;
}
