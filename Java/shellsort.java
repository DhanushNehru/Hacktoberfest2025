public class ShellSort {
    public static void main(String[] args) {
        // An array of integers
        int[] a = {23, 29, 15, 19, 31, 7, 9, 5, 2};
        int nElements = a.length;

        // Printing unsorted array
        System.out.println("Unsorted array:");
        for (int i = 0; i < nElements; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();

        // SHELL sort
        for (int gap = nElements / 2; gap >= 1; gap /= 2) { // Loop for gap
            for (int j = gap; j < nElements; j++) { // For passes
                for (int i = j - gap; i >= 0; i -= gap) { // Comparisons within each pass
                    if (a[i + gap] > a[i]) {
                        break;
                    } else {
                        // Swap a[i+gap] with a[i]
                        int temp = a[i + gap];
                        a[i + gap] = a[i];
                        a[i] = temp;
                    }
                }
            }
        }

        // Printing sorted array
        System.out.println("\nSorted array:");
        for (int i = 0; i < nElements; i++) {
            System.out.print(a[i] + "\t");
        }
    }
}
