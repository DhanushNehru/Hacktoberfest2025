public class allBookAllocation {

    // Function to check if it's possible to allocate books
    private static boolean isFeasible(int[] arr, int n, int m, int mid) {
        int studentsRequired = 1;
        int currentSum = 0;

        for (int i = 0; i < n; i++) {
            // If a single book has more pages than mid, return false
            if (arr[i] > mid) {
                return false;
            }

            // If current sum exceeds mid, allocate to the next student
            if (currentSum + arr[i] > mid) {
                studentsRequired++;
                currentSum = arr[i];

                // If students required exceed m, return false
                if (studentsRequired > m) {
                    return false;
                }
            } else {
                currentSum += arr[i];
            }
        }
        return true;
    }

    // Function to find the minimum number of pages
    public static int findPages(int[] arr, int n, int m) {
        if (m > n) {
            return -1; // Not enough books for students
        }

        int low = 0;
        int high = 0;

        // Initialize low and high for binary search
        for (int pages : arr) {
            low = Math.max(low, pages); // max pages in a single book
            high += pages; // sum of all pages
        }

        int result = high; // Initialize result

        // Binary search to find the minimum of the maximum pages
        while (low <= high) {
            int mid = (low + high) / 2;

            if (isFeasible(arr, n, m, mid)) {
                result = mid; // Update result
                high = mid - 1; // Look for a smaller max
            } else {
                low = mid + 1; // Increase mid
            } 
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] arr = {12, 34, 67, 90};
        int m = 2;

        int result = findPages(arr, n, m);
        System.out.println(result); // Output: 113
    }
}
