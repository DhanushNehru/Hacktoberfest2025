public class ISsort {
    static void ISsorted(int[] arr, int i, int n, boolean issort) {
        if (i == n - 1) {
            System.out.println(issort);
            return;
        }
        if (arr[i] < arr[i + 1]) {
            issort = true;
        } else {
            issort = false;
            System.out.println(issort);
            return;
        }
        ISsorted(arr, i + 1, n, issort);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int i = 0;
        int n = arr.length;
        boolean issort = false;
        ISsorted(arr, i, n, issort);
    }
}
