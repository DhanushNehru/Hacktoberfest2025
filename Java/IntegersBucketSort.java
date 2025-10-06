import java.util.Arrays;

public class IntegersBucketSort {
    public static void bucketSort(int[] arr, int low, int high) {
        int[] hist = new int[high - low + 1];

        for (int num : arr) {
            hist[num - low]++;
        }

        int index = 0;

        for (int i = 0; i < hist.length; i++) {
            for (int j = 0; j < hist[i]; j++) {
                arr[index++] = i + low;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 5, 2, 2, 3, 0, 0, -1, 2};

        System.out.println("Unsorted array: " + Arrays.toString(arr));

        bucketSort(arr, -1, 5);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
