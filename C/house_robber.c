#include <stdio.h>

int rob(int* nums, int numsSize) {
    if (numsSize == 0) return 0;
    if (numsSize == 1) return nums[0];

    int prev2 = 0;      
    int prev1 = 0;      
    for (int i = 0; i < numsSize; ++i) {
        int pick = nums[i] + prev2;   
        int notPick = prev1;         
        int cur = (pick > notPick) ? pick : notPick;
        prev2 = prev1;
        prev1 = cur;
    }
    return prev1; 
}

int main() {
    int nums[] = {2, 7, 9, 3, 1};
    int n = sizeof(nums) / sizeof(nums[0]);
    printf("Max rob amount = %d\n", rob(nums, n)); 
    return 0;
}
