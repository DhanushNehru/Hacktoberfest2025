import java.util.ArrayList;
import java.util.List;

public class Arrsubseq {
    static ArrayList<Integer> printsub(int[] arr, int i) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (i == arr.length) {
            ans.add(0);
            return ans;
        }
        int currelem = arr[i];
        ArrayList<Integer> smallans = printsub(arr, i + 1);
        for (int elem : smallans) {
            ans.add(elem + currelem);
            ans.add(elem);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4 };
        int i = 0;
        ArrayList<Integer> ans = printsub(arr, i);
        System.out.println(ans);
    }
}
