
public class linearsearch {

    public static int search(int numbers[], int key) {
        for (int i = 0; i < numbers.length; i++) {  // Changed from <= to < for proper array bounds
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static int GetLargest(int numbers[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {  // Changed from <= to < for proper array bounds
            if (largest < numbers[i]) {
                largest = numbers[i];
            }
        }
        return largest;
    }

    public static int binary_search(int numbers[], int key) {
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (numbers[mid] == key) {
                return mid;
            }
            if (numbers[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void subarray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                for (int k = start; k <= end; k++) {
                    System.out.print(numbers[k] + " ");
                }
                System.out.println(); // To print each subarray on a new line
            }
            System.out.println(); // To add a line break after each starting index
        }
    }
    
    public static void reverse_array(int numbers[]) {
        int first = 0, last = numbers.length - 1; // Fixed the 'last' index
        while (first < last) {
            // write code for swapping numbers
            int swap = numbers[last];
            numbers[last] = numbers[first];
            numbers[first] = swap;
            first++; // Increment 'first'
            last--;  // Decrement 'last'
        }
    }

    public static void main(String[] args) {
        int numbers[] = {10, 20, 10, 3, 0, 50, 50, 6, 44};
        // int key = 6;
        // int ans = search(numbers, key);
        // System.out.println("Your key is at index: " + ans);
        // int largestValue = GetLargest(numbers);
        // System.out.println("Largest value is: " + largestValue);

        // short array and print binary search 
        // Arrays.sort(numbers);
        // System.out.println("Sorted array: " + Arrays.toString(numbers));
        // System.out.println("The Index of the key id: " + binary_search(numbers, key));

    //     reverse_array(numbers);
    //     for (int i = 0; i < numbers.length; i++) {  // Fixed the loop to start from index 0
    //         System.out.print(numbers[i] + " ");
    //     }
    //     System.out.println("");

    // print subarray
  subarray(numbers);
    
    }
}
