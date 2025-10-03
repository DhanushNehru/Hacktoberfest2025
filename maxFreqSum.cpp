#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxFreqSum(string s) {
        int n = s.length();
        unordered_map<char, int> mp;
        int mv = 0; // max vowel frequency
        int mc = 0; // max consonant frequency

        for (char ch : s) {
            mp[ch]++;
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                mv = max(mv, mp[ch]);
            } else {
                mc = max(mc, mp[ch]);
            }
        }

        return mv + mc;
    }
};

int main() {
    Solution sol;

    string s1 = "abcdeaioouu";
    cout << "Input: " << s1 << "\n";
    cout << "Max frequency sum (vowel + consonant): " 
         << sol.maxFreqSum(s1) << "\n\n";

    string s2 = "leetcode";
    cout << "Input: " << s2 << "\n";
    cout << "Max frequency sum (vowel + consonant): " 
         << sol.maxFreqSum(s2) << "\n\n";

    string s3 = "programming";
    cout << "Input: " << s3 << "\n";
    cout << "Max frequency sum (vowel + consonant): " 
         << sol.maxFreqSum(s3) << "\n";

    return 0;
}
