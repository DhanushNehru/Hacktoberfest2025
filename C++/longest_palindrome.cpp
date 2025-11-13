#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int longestFibonacciSubarray(vector<int>& nums) {
    int n = nums.size();
    if (n <= 2) return n;

    int maxLen = 2, currLen = 2;

    for (int i = 2; i < n; i++) {
        if (nums[i] == nums[i - 1] + nums[i - 2]) {
            currLen++;
        } else {
            currLen = 2; // reset to base case (length 2)
        }
        maxLen = max(maxLen, currLen);
    }

    return maxLen;
}

int main() {
    vector<int> nums = {1, 1, 1, 1, 2, 3, 5, 1};
    cout << "Longest Fibonacci Subarray Length: "
         << longestFibonacciSubarray(nums) << endl;
    return 0;
}
