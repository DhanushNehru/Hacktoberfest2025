import java.util.Scanner;

public class TargLast {
    static void findLastind(int[] arr, int idx, int targ) {
        if (idx == 0) {// if the given element is not present in the arrray............
            System.out.println("Sorry this element is not present in the array");
            return;
        }
        if (targ == arr[idx]) {
            System.out.println(idx);
            return;
        }
        findLastind(arr, idx - 1, targ);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 5, 6, 2, 3, 5, 2, 3, 5 };
        int i = arr.length - 1;
        System.out.println("Enter the which you want to find");
        Scanner sc = new Scanner(System.in);
        int targ = sc.nextInt();
        findLastind(arr, i, targ);
    }
}
