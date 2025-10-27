#include <bits/stdc++.h>
using namespace std;

int binarySearch(vector<int> &v, int x)
{
    int low = 0;
    int high = v.size() - 1;

    while (low <= high)
    {
        int mid = low + (high - low) / 2;

        if (v[mid] == x)
        {
            return mid;
        }
        else if (v[mid] < x)
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

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n;
    cout << "Enter number of elements: ";
    cin >> n;

    vector<int> v(n);
    cout << "Enter " << n << endl;
    for (int i = 0; i < n; i++)
    {
        cin >> v[i];
    }

    int x;
    cout << "Enter element to search: ";
    cin >> x;

    sort(v.begin(), v.end());

    int result = binarySearch(v, x);

    if (result == -1)
    {
        cout << "Element is not present in array" << endl;
    }
    else
    {
        cout << "Element is present at index " << result << endl;
    }

    return 0;
}