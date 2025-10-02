public class SubsequenceM2 {
    static void Subsequence2(String str, String currans) {
        if (str.length() == 0) {
            System.out.println(currans);
            return;
        }
        char currcr2 = str.charAt(0);
        String str2 = str.substring(1);
        Subsequence2(str2, currans + currcr2);
        Subsequence2(str2, currans);
    }

    public static void main(String[] args) {
        String str = "abc";
        String currans = "";
        Subsequence2(str, currans);
    }
}
