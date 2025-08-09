public class selection_sort {
    public static void sort(int n[]) {
        for (int i = 0; i < n.length - 1; i++) {
            int minpos = i; // Corrected: minpos should be initialized to i
            for (int j = i + 1; j < n.length; j++) {
                if (n[minpos] > n[j]) {
                    minpos = j;
                }
            }
            int temp = n[minpos];
            n[minpos] = n[i];
            n[i] = temp;
        }
    }

    public static void printarr(int n[]) {
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i] + " "); // Added space for better readability
        }
        System.out.println("");
    }

    // ******to sort array with input******
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int count = 5;
    //     System.out.println("Enter your Array:");
    //     int n[] = new int[count];
    //     for (int i = 0; i < count; i++) {
    //         n[i] = sc.nextInt();
    //     }
    //     System.out.print(Arrays.toString(n));
    //     System.out.println("");
    //     sort(n);
    //     printarr(n);
    // }

    public static void main(String[] args) {
        int n[] = {1, 5, 2, 6, 3, 4, 8};
        sort(n);
        printarr(n);
    }
}
