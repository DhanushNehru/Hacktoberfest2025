public class Arrsum {
    static int Findmax(int[] arr, int i, int sum) {
        if (i == arr.length - 1) {
            return sum += arr[i];
        }
        int ans = Findmax(arr, i + 1, sum + arr[i]);// we can also do (arr,i+1;sum) ans return ans +
                                                    // arr[i]..................
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        int i = 0;
        int sum = 0;
        System.out.println(Findmax(arr, i, sum));
    }
}
