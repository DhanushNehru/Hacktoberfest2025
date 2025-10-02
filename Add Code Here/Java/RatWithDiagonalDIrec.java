import java.util.*;

public class RatWithDiagonalDIrec {
    public static ArrayList<ArrayList<String>> PrintPaths(int i, int j, int r, int c, ArrayList<String> anr) {
        if (i > r || j > c)
            return new ArrayList<>();
        if (i == r && j == c) {
            ArrayList<ArrayList<String>> ans = new ArrayList<>();
            ArrayList<String> arr = new ArrayList<>(anr);
            ans.add(arr);
            return ans;
        }
        anr.add("R");
        ArrayList<ArrayList<String>> a = PrintPaths(i, j + 1, r, c, anr);
        anr.remove(anr.size() - 1);
        anr.add("D");
        ArrayList<ArrayList<String>> b = PrintPaths(i + 1, j, r, c, anr);
        anr.remove(anr.size() - 1);
        anr.add("C");
        ArrayList<ArrayList<String>> g = PrintPaths(i + 1, j + 1, r, c, anr);
        anr.remove(anr.size() - 1);
        a.addAll(b);
        a.addAll(g);
        return a;
    }

    public static void main(String[] args) {
        int r = 2;
        int c = 3;
        int i = 0;
        int j = 0;
        String ans = "";
        System.out.println(PrintPaths(i, j, r, c, new ArrayList<>()));
    }
}
