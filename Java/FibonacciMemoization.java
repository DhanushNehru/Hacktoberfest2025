
/**
 * Fibonacci Number Calculator using Dynamic Programming (Memoization)
 * 
 * Problem: Calculate nth Fibonacci number efficiently
 * Approach: Top-down DP with memoization to avoid recalculation
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * @author Prince Yadav
 * @version 1.0
 * @since October 2025
 */
public class FibonacciMemoization {
    
    // Cache array to store computed Fibonacci numbers
    private static long[] memo;
    
    /**
     * Calculates nth Fibonacci number using memoization
     * Formula: F(n) = F(n-1) + F(n-2)
     * Base cases: F(0) = 0, F(1) = 1
     * 
     * @param n The position in Fibonacci sequence (0-indexed)
     * @return The nth Fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Return cached value if already computed
        if (memo[n] != 0) {
            return memo[n];
        }
        
        // Compute and cache the result
        long fibNumber = fibonacci(n - 1) + fibonacci(n - 2);
        memo[n] = fibNumber;
        
        return fibNumber;
    }
    
    /**
     * Alternative iterative approach (more efficient)
     * Time: O(n), Space: O(1)
     * 
     * @param n Position in sequence
     * @return nth Fibonacci number
     */
    public static long fibonacciIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        
        if (n <= 1) {
            return n;
        }
        
        long prev2 = 0;
        long prev1 = 1;
        long current = 0;
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return current;
    }
    
    /**
     * Initializes memoization array
     * 
     * @param size Size of memo array (n+1)
     */
    private static void initializeMemo(int size) {
        memo = new long[size + 1];
    }
    
    /**
     * Prints the entire Fibonacci sequence up to n
     * 
     * @param n Upper limit
     */
    public static void printFibonacciSequence(int n) {
        System.out.println("Fibonacci Sequence up to F(" + n + "):");
        for (int i = 0; i <= n; i++) {
            System.out.printf("F(%d) = %d%n", i, memo[i]);
        }
    }
    
    /**
     * Main method demonstrating usage with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Basic calculation
        System.out.println("=== Test Case 1: Calculate F(50) ===");
        int n = 50;
        initializeMemo(n);
        
        long result = fibonacci(n);
        System.out.println("F(" + n + ") = " + result);
        System.out.println();
        
        // Test Case 2: Print first 15 Fibonacci numbers
        System.out.println("=== Test Case 2: First 15 Numbers ===");
        initializeMemo(15);
        for (int i = 0; i <= 15; i++) {
            System.out.printf("F(%2d) = %d%n", i, fibonacci(i));
        }
        System.out.println();
        
        // Test Case 3: Compare both approaches
        System.out.println("=== Test Case 3: Compare Approaches ===");
        int testN = 40;
        initializeMemo(testN);
        
        long startTime = System.nanoTime();
        long memoResult = fibonacci(testN);
        long memoTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long iterResult = fibonacciIterative(testN);
        long iterTime = System.nanoTime() - startTime;
        
        System.out.println("Memoization: F(" + testN + ") = " + memoResult + 
                         " (Time: " + memoTime/1000 + " μs)");
        System.out.println("Iterative:   F(" + testN + ") = " + iterResult + 
                         " (Time: " + iterTime/1000 + " μs)");
        System.out.println();
        
        // Test Case 4: Edge cases
        System.out.println("=== Test Case 4: Edge Cases ===");
        System.out.println("F(0) = " + fibonacciIterative(0)); // Expected: 0
        System.out.println("F(1) = " + fibonacciIterative(1)); // Expected: 1
        System.out.println("F(2) = " + fibonacciIterative(2)); // Expected: 1
    }
}