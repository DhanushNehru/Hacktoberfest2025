public class Arrsumm2 {// this approach would also work fantastic...............
    static int Findmax(int[] arr, int i) {
        if (i == arr.length) {
            return 0;
        }
        int ans = Findmax(arr, i + 1);
        return ans + arr[i];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        int i = 0;
        System.out.println(Findmax(arr, i));
    }
}
