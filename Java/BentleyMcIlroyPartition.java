import java.util.Arrays;
//Dutch national flag algorithm variation
//Partitions array into three parts: < pivot, == pivot, > pivot (i.e sorted order)
// Time complexity: O(n)
// Space complexity: O(1)
public class BentleyMcIlroyPartition {
    public static void bentleyMcIlroyPartition(int[] arr, int pivot) {
        int n = arr.length;
        int i = 0, j = n - 1;
        int p = 0, q = n - 1;
        while (true) { 
            while (i <= j && arr[i] < pivot) i++;
            while (i <= j && arr[j] > pivot) j--;

            if (i > j) break;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            if (arr[i] == pivot) {
                temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;
                p++;
            }
            if (arr[j] == pivot) {
                temp = arr[j];
                arr[j] = arr[q];
                arr[q] = temp;
                q--;
            }

            i++;
            j--;
        }

        int left = j;
        for (int k = 0; k < p; k++, left--) {
            int temp = arr[k];
            arr[k] = arr[left];
            arr[left] = temp;
        }

        int right = i;
        for (int k = n - 1; k > q; k--, right++) {
            int temp = arr[k];
            arr[k] = arr[right];
            arr[right] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 9, 4, 8, 4, 2, 4, 7, 4, 6};
        int pivot = 4;

        bentleyMcIlroyPartition(arr, pivot);

        System.out.println(Arrays.toString(arr));
    }
}
