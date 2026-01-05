class Solution {

    // Function to find indices of Next Smaller Element (NSE)
    private int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are greater or equal to current
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack is empty, NSE doesn't exist → set to n
            ans[i] = !st.isEmpty() ? st.peek() : n;

            // Push current index to stack
            st.push(i);
        }

        // Return NSE indices
        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Element (PSEE)
    private int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements greater than current
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack is empty, PSEE doesn't exist → set to -1
            ans[i] = !st.isEmpty() ? st.peek() : -1;

            // Push current index to stack
            st.push(i);
        }

        // Return PSEE indices
        return ans;
    }

    // Function to compute the sum of minimums in all subarrays
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        // Get NSE and PSEE indices
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);

        // Modulo for large results
        int mod = (int)1e9 + 7;
        int sum = 0;

        // Traverse each element to compute its contribution
        for (int i = 0; i < n; i++) {
            // Count of elements to the left including current
            int left = i - psee[i];

            // Count of elements to the right including current
            int right = nse[i] - i;

            // Total subarrays where arr[i] is the minimum
            long freq = left * right * 1L;

            // Contribution = frequency * value
            int val = (int)((freq * arr[i]) % mod);

            // Add contribution to sum
            sum = (sum + val) % mod;
        }

        // Return the final sum
        return sum;
    }
}
