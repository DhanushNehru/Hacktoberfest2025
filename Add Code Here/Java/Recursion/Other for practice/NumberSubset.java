import java.util.*;

public class NumberSubset {
    static boolean Subset(String str, int l, int r) {
        if (l == r) {
            return true;
        }
        if (str.charAt(l) != str.charAt(r))
            return false;
        else
            return Subset(str, l + 1, r - 1);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        String ans = "tatat";
        System.out.println(Subset(ans, 0, ans.length() - 1));
    }
}
