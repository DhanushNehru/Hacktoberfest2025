public class Printarr {
    static void printarr(int[] arr, int i) {
        if (i == 0) {
            System.out.println(arr[i]);
            return;
        }
        printarr(arr, i - 1);
        System.out.println(arr[i]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int i = arr.length;
        printarr(arr, i - 1);
    }
}
