import java.util.*;

public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }
        return -1; // This means the target element  not found
    }

    public static void main(String[] args) {
      Scanner sc = new Scanner("System.in");
      System.out.println("Enter the number of elements");
      int n = sc.nextInt();
      int arr[] = new int[n];  // Try this as I/P :  {1, 3, 5, 7, 9, 11};
      for(int i = 0 ; i < arr.length ; i++){
        arr[i] = sc.nextInt();
      }
      System.out.println("Enter the target");
       int target = sc.nextInt();
       int result = binarySearch(arr, target);

        if (result == -1) {
            System.out.println("Element not present in the array.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
