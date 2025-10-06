import java.util.Scanner;

public class BookAllocation {

    public static long findPages(int n, int[] arr, int m) {
        // Check if number of students is more than the number of books
        if (m > n) return -1;

        // Initialize variables
        int end = 0;
        long ans = 0;
        int start = Integer.MIN_VALUE;

        // Calculate the total pages (end) and the maximum pages in a single book (start)
        for (int i = 0; i < n; i++) {
            end += arr[i];
            start = Math.max(start, arr[i]);
        }

        // Perform binary search to find the minimum of the maximum pages
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int pages = 0;
            int count = 1;

            // Check if current mid value can be used to allocate books
            for (int i = 0; i < n; i++) {
                pages += arr[i];
                if (pages > mid) {
                    count++;
                    pages = arr[i];
                }
            }

            // Update the answer based on the number of students required
            if (count <= m) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input the number of books
        System.out.println("Enter the number of books:");
        int n = sc.nextInt();

        // Step 2: Input the number of pages in each book
        int[] arr = new int[n];
        System.out.println("Enter the number of pages in each book:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Step 3: Input the number of students
        System.out.println("Enter the number of students:");
        int m = sc.nextInt();

        // Step 4: Find and display the minimum number of maximum pages allocated
        long result = findPages(n, arr, m);
        System.out.println("Minimum number of maximum pages allocated: " + result);
        sc.close();
    }
}
