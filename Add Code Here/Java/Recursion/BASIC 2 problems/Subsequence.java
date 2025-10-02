import java.util.ArrayList;

public class Subsequence {
    static ArrayList<String> PrintSubsq(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str.length() == 0) {
            ans.add("");
            return ans;
        }
        char currcr = str.charAt(0);
        ArrayList<String> smallans = PrintSubsq(str.substring(1));
        for (String st : smallans) {// Example of for each loop....................
            ans.add(st);
            ans.add(currcr + st);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abc";
        ArrayList<String> ans = PrintSubsq(str);
        for (String elem : ans) {// This will print each element one by one ................................
            System.out.println(elem);
        }
    }
}