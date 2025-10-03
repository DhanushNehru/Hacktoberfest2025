package dsa;

/**
 * Solution for finding missing number in array containing n-1 elements
 * from range 1 to N.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * @author Prince Yadav
 * @date October 2025
 */
public class MissingNumber {
    
    /**
     * Finds missing number using mathematical sum formula
     * Formula: Sum of first N natural numbers = N * (N + 1) / 2
     * 
     * @param arr Array containing n-1 numbers from 1 to N
     * @param N The upper limit of the range
     * @return The missing number
     * @throws IllegalArgumentException if array is null or N is invalid
     */
    public static int findMissingNumberSumFormula(int[] arr, int N) {
        if (arr == null || N < 1) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        int totalSum = N * (N + 1) / 2;
        int arrSum = 0;
        for (int num : arr) {
            arrSum += num;
        }
        return totalSum - arrSum;
    }
    
    /**
     * Alternative approach using XOR for better overflow handling
     * XOR properties: a ^ a = 0, a ^ 0 = a
     * 
     * @param arr Array containing n-1 numbers
     * @param N Upper limit
     * @return Missing number
     */
    public static int findMissingNumberXOR(int[] arr, int N) {
        if (arr == null || N < 1) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        int xor = 0;
        
        // XOR all array elements
        for (int num : arr) {
            xor ^= num;
        }
        
        // XOR with all numbers from 1 to N
        for (int i = 1; i <= N; i++) {
            xor ^= i;
        }
        
        return xor;
    }
    
    /**
     * Test cases demonstrating both approaches
     */
    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] arr1 = {2, 3, 1, 5};
        int N1 = 5;
        System.out.println("Test 1 - Sum Formula: " + 
            findMissingNumberSumFormula(arr1, N1)); // Expected: 4
        System.out.println("Test 1 - XOR Method: " + 
            findMissingNumberXOR(arr1, N1)); // Expected: 4
        
        // Test Case 2: Missing first element
        int[] arr2 = {2, 3, 4, 5};
        int N2 = 5;
        System.out.println("\nTest 2 - Sum Formula: " + 
            findMissingNumberSumFormula(arr2, N2)); // Expected: 1
        
        // Test Case 3: Missing last element
        int[] arr3 = {1, 2, 3, 4};
        int N3 = 5;
        System.out.println("\nTest 3 - Sum Formula: " + 
            findMissingNumberSumFormula(arr3, N3)); // Expected: 5
        
        // Test Case 4: Large numbers
        int[] arr4 = new int[99];
        for (int i = 0; i < 99; i++) {
            arr4[i] = i + 1; // 1 to 99
        }
        int N4 = 100;
        System.out.println("\nTest 4 - Large array: " + 
            findMissingNumberSumFormula(arr4, N4)); // Expected: 100
    }
}