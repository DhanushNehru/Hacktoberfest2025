public class KadanesAlgorithm {

    // Function to implement Kadane's Algorithm
    public static int maxSubArraySum(int[] arr) {
        // Initialize variables to track the current sum and the maximum sum
        int maxSoFar = arr[0];  // Maximum sum found so far
        int currentMax = arr[0]; // Maximum sum ending at the current position

        // Loop through the array starting from the second element
        for (int i = 1; i < arr.length; i++) {
            // Update currentMax to be the maximum between the current element
            // and the sum of the current element with the previous currentMax
            currentMax = Math.max(arr[i], currentMax + arr[i]);

            // Update maxSoFar if currentMax is larger
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar; // Return the maximum sum of the contiguous subarray
    }

    // Main function to test the algorithm
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // Sample array

        // Call Kadane's algorithm and print the result
        int maxSum = maxSubArraySum(arr);
        System.out.println("Maximum contiguous subarray sum is: " + maxSum);
    }
}
