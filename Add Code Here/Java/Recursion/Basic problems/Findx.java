import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Findx {
    static List<Integer> findx(int[] arr, int n, int i, int x, List<Integer> ans) {
        if (i >= n) {
            // return false;
            return ans;
        }
        if (x == arr[i]) {
            // return true;
            ans.add(i);
        }
        return findx(arr, n, i + 1, x, ans);// this will return true of false.....................
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = { 3, 5, 6, 45, 46, 7, 63, 8, 6, 5, 4, 3 };
        int i = 0;
        System.out.println("Which element do you want to find");
        int x = sc.nextInt();
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println(findx(arr, n, i, x, ans));
        // if (findx(arr, n, i, x)) {
        // System.out.println("Yes it's present");
        // } else {
        // System.out.println("No it's not present");
        // }
    }
}
