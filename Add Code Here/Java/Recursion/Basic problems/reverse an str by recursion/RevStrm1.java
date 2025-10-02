public class RevStrm1 {
    static String ReverseStr(String str, int i) {
        if (i == str.length()) {
            return "";
        }
        String smallans = ReverseStr(str, i + 1);
        char currchar = str.charAt(i);
        return smallans + currchar;
    }

    public static void main(String[] args) {
        String str = "Vanshson";
        int i = 0;
        System.out.println(ReverseStr(str, i));
    }
}
