#include <iostream>
#include <math.h>
using namespace std;

int findMin(vector<int> &nums)
{
    int low = 0, high = nums.size() - 1, ans = INT_MAX;

    while (low <= high)
    {
        int mid = (low + high) / 2;

        if (nums[mid] == nums[low] && nums[mid] == nums[high])
        {
            ans = min(ans, nums[low]);
            high--;
            low++;
            continue;
        }

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
    vector<int> nums = {1};
    cout << "findMin : " << findMin(nums) << endl;
    return 0;
}