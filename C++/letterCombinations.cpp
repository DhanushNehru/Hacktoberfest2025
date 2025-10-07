#include <bits/stdc++.h>
using namespace std;

unordered_map<char, string> phoneMap = {
    {'2', "abc"}, {'3', "def"}, {'4', "ghi"}, {'5', "jkl"}, {'6', "mno"}, {'7', "pqrs"}, {'8', "tuv"}, {'9', "wxyz"}};

void calcCombinations(int n, int i, string s, string digits,
                      vector<string> &ans)
{
    if (i == n)
    {
        if (!s.empty())
            ans.push_back(s);
        return;
    }

    for (char ch : phoneMap[digits[i]])
    {
        s.push_back(ch);
        calcCombinations(n, i + 1, s, digits, ans);
        s.pop_back();
    }
}

vector<string> letterCombinations(string digits)
{
    vector<string> ans;
    int n = digits.size();
    calcCombinations(n, 0, "", digits, ans);
    return ans;
}