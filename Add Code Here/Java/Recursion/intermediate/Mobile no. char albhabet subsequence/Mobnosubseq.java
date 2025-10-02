import java.util.ArrayList;

public class Mobnosubseq {
    static void printPattern(String[] patt, String str, String ans) {
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        int currelem = str.charAt(0) - '0';
        String currpatt = patt[currelem];
        for (int i = 0; i < currpatt.length(); i++) {
            printPattern(patt, str.substring(1), ans + currpatt.charAt(i));
        }
    }

    public static void main(String[] args) {
        String[] patt = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        String str = "23";
        String ans = "";
        printPattern(patt, str, ans);
    }
}