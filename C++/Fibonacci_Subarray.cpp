#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int longestFibonacciSubarray(vector<int>& nums) {
        int n = nums.size();
        if (n <= 2) return n;

        int maxLen = 2;
        int currLen = 2;

        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] + nums[i - 2])
                currLen++;
            else
                currLen = 2;  // restart counting

            maxLen = max(maxLen, currLen);
        }
        return maxLen;
    }
};

int main() {
    Solution sol;
    vector<int> nums1 = {1, 1, 1, 1, 2, 3, 5, 1};
    vector<int> nums2 = {5, 2, 7, 9, 16};
    vector<int> nums3 = {1000000000, 1000000000, 1000000000};

    cout << sol.longestFibonacciSubarray(nums1) << endl; // 5
    cout << sol.longestFibonacciSubarray(nums2) << endl; // 5
    cout << sol.longestFibonacciSubarray(nums3) << endl; // 2
}
