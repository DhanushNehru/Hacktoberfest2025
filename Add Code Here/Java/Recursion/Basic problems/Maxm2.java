public class Maxm2 {
    static int Findmax(int[] arr, int i) {
        if (i == arr.length - 1) {
            return arr[i];
        }
        int smallval = Findmax(arr, i + 1);
        return Math.max(smallval, arr[i]);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 5, 7, 8, 6, 5, 4 };
        int i = 0;
        System.out.println(Findmax(arr, i));
    }
}
