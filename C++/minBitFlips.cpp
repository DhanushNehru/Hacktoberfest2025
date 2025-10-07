#include <bits/stdc++.h>
using namespace std;

int minBitFlips(int start, int goal)
{
    int ans = 0, cnt = 0;
    while (start != goal)
    {
        if ((start & (1 << cnt)) != (goal & (1 << cnt)))
        {
            start = start ^ (1 << cnt);
            ans++;
        }
        cnt++;
    }
    return ans;
}