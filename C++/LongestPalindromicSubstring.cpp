#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

// Function to expand around the center and return palindrome length
int expandFromCenter(const string &s, int left, int right) {
    while (left >= 0 && right < s.size() && s[left] == s[right]) {
        left--;
        right++;
    }
    return right - left - 1;  // length of palindrome
}

// Function to find the longest palindromic substring
string longestPalindrome(string s) {
    if (s.empty()) return "";

    int start = 0, end = 0;

    for (int i = 0; i < s.size(); i++) {
        int len1 = expandFromCenter(s, i, i);     // Odd-length palindrome
        int len2 = expandFromCenter(s, i, i + 1); // Even-length palindrome
        int len = max(len1, len2);

        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }

    return s.substr(start, end - start + 1);
}

// Main function
int main() {
    string s;
    cout << "Enter a string: ";
    cin >> s;

    string result = longestPalindrome(s);
    cout << "Longest Palindromic Substring: " << result << endl;

    return 0;
}
