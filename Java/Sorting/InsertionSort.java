// Efficient for small arrays or nearly sorted data.
// Time Complexity: O(n^2)
//  Best Case: O(n) 
//  Space Complexity: O(1)

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 5, 1, 4, 3};
        insertionSort(arr);
        for (int num : arr) System.out.print(num + " ");
    }
}