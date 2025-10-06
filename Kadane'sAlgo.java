class Solution {
    public int maxSubArray(int[] nums) {
        int currs =0;
        int maxs = nums[0];
        for(int i : nums){
            currs = currs+i;
            maxs = currs>maxs ? currs:maxs;
            if(currs<0){
                currs = 0;
            }
        }
        return maxs;
    }
}
/////////////////////////////
public class KadaneAlgorithm {
    public static int maxSubArraySum(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum sum of contiguous subarray: " + maxSubArraySum(arr));
    }
}
