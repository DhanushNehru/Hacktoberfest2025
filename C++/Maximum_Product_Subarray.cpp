#include <bits/stdc++.h>
using namespace std;

/*
  Problem: Maximum Product Subarray
  Time Complexity: O(n)
  Space Complexity: O(1)
*/

int maxProduct(vector<int>& nums) {
    int n = nums.size();
    int maxProd = nums[0];
    int minProd = nums[0];
    int ans = nums[0];

    for (int i = 1; i < n; i++) {
        // Store before changing maxProd
        int tempMax = maxProd;

        maxProd = max({nums[i], nums[i] * maxProd, nums[i] * minProd});
        minProd = min({nums[i], nums[i] * tempMax, nums[i] * minProd});

        ans = max(ans, maxProd);
    }
    return ans;
}

int main() {
    vector<int> nums = {2, 3, -2, 4};
    cout << "Maximum Product Subarray = " << maxProduct(nums) << endl;
    return 0;
}
