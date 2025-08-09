public class pairsinarray {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 7, 4, 5};
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {  // Corrected condition
                System.out.print("(" + arr[i] + "," + arr[j] + ")");
            }System.out.println("");
        }
    }
}
