#include <bits/stdc++.h>
using namespace std;

int beautySum(string s)
{
    int n = s.size(), sum = 0;
    for (int i = 0; i < n; i++)
    {
        vector<int> hash(26, 0);
        for (int j = i; j < n; j++)
        {
            hash[s[j] - 'a']++;

            int maxFreq = INT_MIN, minFreq = INT_MAX;
            for (int f : hash)
            {
                if (f > 0)
                {
                    maxFreq = max(maxFreq, f);
                    minFreq = min(minFreq, f);
                }
            }
            sum += (maxFreq - minFreq);
        }
    }
    return sum;
}