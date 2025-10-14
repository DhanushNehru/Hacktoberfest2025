import java.util.*;
import java.util.stream.*;

public class AdvancedSorter {
    private static int stepCount = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("⚡ Advanced Hybrid Sorter");
        System.out.print("Enter array size (10-50): ");
        int size = scanner.nextInt();
        
        int[] array = random.ints(size, 1, 100).toArray();
        System.out.println("Original: " + Arrays.toString(array));
        
        System.out.println("\n1. TimSort (Hybrid)");
        System.out.println("2. IntroSort (Adaptive)");
        System.out.println("3. Dual-Pivot QuickSort");
        System.out.print("Choose algorithm (1-3): ");
        
        int choice = scanner.nextInt();
        int[] sorted = sortArray(array.clone(), choice);
        
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println("Steps: " + stepCount);
        analyzePerformance(array, sorted);
    }
    
    private static int[] sortArray(int[] arr, int algorithm) {
        stepCount = 0;
        switch(algorithm) {
            case 1: return timSort(arr);
            case 2: return introSort(arr);
            case 3: return dualPivotQuickSort(arr, 0, arr.length-1);
            default: return arr;
        }
    }
    
    private static int[] timSort(int[] arr) {
        int RUN = 32;
        int n = arr.length;
        
        // Insertion sort on small runs
        for (int i = 0; i < n; i += RUN) {
            insertionSort(arr, i, Math.min(i + RUN - 1, n - 1));
        }
        
        // Merge sorted runs
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
        return arr;
    }
    
    private static int[] introSort(int[] arr) {
        int depthLimit = (int) (2 * Math.log(arr.length) / Math.log(2));
        return introSort(arr, 0, arr.length-1, depthLimit);
    }
    
    private static int[] introSort(int[] arr, int low, int high, int depth) {
        if (high - low < 16) {
            insertionSort(arr, low, high);
            return arr;
        }
        if (depth == 0) {
            heapSort(arr, low, high);
            return arr;
        }
        
        int pivot = partition(arr, low, high);
        introSort(arr, low, pivot-1, depth-1);
        introSort(arr, pivot+1, high, depth-1);
        return arr;
    }
    
    private static int[] dualPivotQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pivots = dualPartition(arr, low, high);
            dualPivotQuickSort(arr, low, pivots[0] - 1);
            dualPivotQuickSort(arr, pivots[0] + 1, pivots[1] - 1);
            dualPivotQuickSort(arr, pivots[1] + 1, high);
        }
        return arr;
    }
    
    private static int[] dualPartition(int[] arr, int low, int high) {
        if (arr[low] > arr[high]) swap(arr, low, high);
        
        int lt = low + 1, gt = high - 1, i = low + 1;
        int pivot1 = arr[low], pivot2 = arr[high];
        
        while (i <= gt) {
            if (arr[i] < pivot1) {
                swap(arr, i++, lt++);
            } else if (arr[i] > pivot2) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
            stepCount++;
        }
        swap(arr, low, --lt);
        swap(arr, high, ++gt);
        
        return new int[]{lt, gt};
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
            stepCount++;
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                stepCount++;
            }
            arr[j + 1] = key;
        }
    }
    
    private static void heapSort(int[] arr, int low, int high) {
        for (int i = (high - low) / 2 + low; i >= low; i--) {
            heapify(arr, high - low + 1, i, low);
        }
        for (int i = high; i > low; i--) {
            swap(arr, low, i);
            heapify(arr, i - low, low, low);
        }
    }
    
    private static void heapify(int[] arr, int n, int i, int offset) {
        int largest = i;
        int left = 2 * (i - offset) + 1 + offset;
        int right = 2 * (i - offset) + 2 + offset;
        
        if (left < offset + n && arr[left] > arr[largest]) largest = left;
        if (right < offset + n && arr[right] > arr[largest]) largest = right;
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest, offset);
        }
        stepCount++;
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        
        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            arr[k++] = (leftArr[i] <= rightArr[j]) ? leftArr[i++] : rightArr[j++];
            stepCount++;
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        stepCount++;
    }
    
    private static void analyzePerformance(int[] original, int[] sorted) {
        boolean isSorted = IntStream.range(0, sorted.length-1)
            .allMatch(i -> sorted[i] <= sorted[i+1]);
        System.out.println("Correctly sorted: " + isSorted);
        System.out.println("Efficiency: " + (stepCount < original.length * original.length ? "Good" : "High steps"));
    }
}
