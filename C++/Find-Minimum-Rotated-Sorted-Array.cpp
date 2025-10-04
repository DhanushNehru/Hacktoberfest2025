#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

int findMin(vector<int> &nums)
{
    int low = 0, high = nums.size() - 1, ans = INT_MAX;

    while (low <= high)
    {
        int mid = (low + high) / 2;

        // complete sorted
        if (nums[low] <= nums[high])
        {
            ans = min(ans, nums[low]);
        }

        // left sorted
        if (nums[low] <= nums[mid])
        {
            ans = min(ans, nums[low]);
            low = mid + 1;
        }
        else
        {
            ans = min(ans, nums[mid]);
            high = mid - 1;
        }
    }
    return ans;
}

int main()
{
    vector<int> nums = {4, 5, 6, 7, 0, 1, 2, 3};
    cout << "findMin : " << findMin(nums) << endl;
    return 0;
}