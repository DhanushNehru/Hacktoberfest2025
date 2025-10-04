#include <iostream>
using namespace std;
// BruteForce
int nthRootBrut(int n, int m)
{
   

    if (m == 0 || n == 1 || m == 1)
    {
        return m;
    }

    for (int i = 1; i <= m; i++)
    {
        int ans = 1;
        for (int j = 1; j <= n; j++)
        {
            ans *= i;
        }
        if (ans == m)
        {
            return i;
        }
        if(ans > m){
            return -1;
        }
    }
    return -1;
}

int nthRootOptimal(int n, int m)
{
    if (m == 0 || n == 1 || m == 1)
    {
        return m;
    }

    int low = 1, high = m;
    while (low <= high)
    {
        int mid = (low + high) / 2;
        int ans = 1;

        for (int i = 1; i <= n; i++)
        {
            ans *= mid;
        }
        if (ans == m)
        {
            return mid;
        }

        if (ans < m)
        {
            low = mid + 1;
        }
        else
        {
            high = mid - 1;
        }
    }

    return -1;
}
int main()
{
    int n = 3, m = 9;
    cout << "nthRoot : " << nthRootBrut(n, m) << endl;
    return 0;
}