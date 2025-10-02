public class Maxvalue {
    static void Findmax(int[] arr, int i, int max) {
        if (i == arr.length) {
            System.out.println("The maximum value is " + max);
            return;
        }
        if (arr[i] > max) {
            max = arr[i];
        }
        Findmax(arr, i + 1, max);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5, 2, 6, 2 };
        int i = 0;
        int max = -100000;
        Findmax(arr, i, max);
    }
}
