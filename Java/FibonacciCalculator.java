/**
 * Fibonacci Calculator
 * Provides efficient methods to calculate Fibonacci numbers
 */
public class FibonacciCalculator {

    /**
     * Calculates the nth Fibonacci number using iteration
     * Time complexity: O(n)
     * Space complexity: O(1)
     * @param n the position in Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public static long fibonacciIterative(int n) {
        if (n <= 1) return n;
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * Calculates the nth Fibonacci number using memoization
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param n the position in Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public static long fibonacciMemo(int n) {
        long[] memo = new long[n + 1];
        return fibonacciMemoHelper(n, memo);
    }

    private static long fibonacciMemoHelper(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("Fibonacci Numbers:");
        for (int i = 0; i <= 10; i++) {
            System.out.println("F(" + i + ") = " + fibonacciIterative(i));
        }
    }
}
