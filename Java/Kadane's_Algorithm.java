public class KadanesAlgorithm {

    // Function to find the maximum sum of a contiguous subarray
    public static int maxSubArraySum(int[] nums) {
        // Initialize maxSoFar to the first element (to handle cases where all elements are negative)
        int maxSoFar = nums[0];
        
        // maxEndingHere tracks the maximum subarray sum ending at the current position
        int maxEndingHere = nums[0];

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Update maxEndingHere: 
            // Either add the current element to the existing subarray or start a new subarray at the current element
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);

            // Update maxSoFar if maxEndingHere is greater than the current maxSoFar
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        // Return the maximum sum of any contiguous subarray
        return maxSoFar;
    }

    // Main method to test the function with multiple test cases
    public static void main(String[] args) {
        // Test Case 1: Contains both positive and negative numbers
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test Case 1 - Maximum subarray sum: " + maxSubArraySum(test1)); // Expected: 6

        // Test Case 2: All positive numbers
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 2 - Maximum subarray sum: " + maxSubArraySum(test2)); // Expected: 15

        // Test Case 3: All negative numbers
        int[] test3 = {-1, -2, -3, -4};
        System.out.println("Test Case 3 - Maximum subarray sum: " + maxSubArraySum(test3)); // Expected: -1
    }
}

