/**
 * ZeroOneKnapsack.java
 * Implementation of 0/1 Knapsack problem using Dynamic Programming
 * 
 * The 0/1 Knapsack problem: Given weights and values of n items, 
 * we need to put these items in a knapsack of capacity W to get
 * the maximum total value in the knapsack.
 */

import java.util.Scanner;

public class ZeroOneKnapsack {
    
    // Returns the maximum value that can be put in a knapsack of capacity W
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        // Create a 2D array to store the maximum value for each subproblem
        int[][] dp = new int[n + 1][W + 1];
        
        // Build the dp table in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or no capacity
                } else if (weights[i - 1] <= w) {
                    // Choose the maximum between including the current item or excluding it
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]],  // Include the item
                        dp[i - 1][w]                                    // Exclude the item
                    );
                } else {
                    // If the current item is too heavy, we can't include it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // Print the selected items
        System.out.println("Selected items:");
        printSelectedItems(dp, weights, values, n, W);
        
        // Return the maximum value
        return dp[n][W];
    }
    
    // Function to print the selected items
    private static void printSelectedItems(int[][] dp, int[] weights, int[] values, int n, int W) {
        int res = dp[n][W];
        int w = W;
        
        for (int i = n; i > 0 && res > 0; i--) {
            // If the result comes from the top, the item is not included
            if (res != dp[i - 1][w]) {
                System.out.println("Item " + i + " with weight " + weights[i - 1] + 
                                   " and value " + values[i - 1] + " is selected.");
                
                // Subtract the value of the item and move to the remaining weight
                res = res - values[i - 1];
                w = w - weights[i - 1];
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = 0;
        while (true) {
            System.out.print("Enter the number of items: ");
            n = scanner.nextInt();
            if (n > 0) {
                break;
            } else {
                System.out.println("Number of items must be greater than 0. Please try again.");
            }
        }
        
        int W = 0;
        while (true) {
            System.out.print("Enter the maximum weight capacity: ");
            W = scanner.nextInt();
            if (W > 0) {
                break;
            } else {
                System.out.println("Maximum weight capacity must be greater than 0. Please try again.");
            }
        }
        
        int[] values = new int[n];
        int[] weights = new int[n];
        
        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Value of item " + (i + 1) + ": ");
                int value = scanner.nextInt();
                if (value >= 0) {
                    values[i] = value;
                    break;
                } else {
                    System.out.println("Value must be non-negative. Please try again.");
                }
            }
        }
        
        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Weight of item " + (i + 1) + ": ");
                int weight = scanner.nextInt();
                if (weight >= 0) {
                    weights[i] = weight;
                    break;
                } else {
                    System.out.println("Weight must be non-negative. Please try again.");
                }
            }
        }
        
        int maxValue = knapsack(W, weights, values, n);
        System.out.println("Maximum value that can be obtained: " + maxValue);
        
        scanner.close();
    }
}
