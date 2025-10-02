public class Removeocc {
    static String removeocc(String str, int n, int i, String ans) {
        if (i == n) {
            return ans;
        }
        if (str.charAt(i) != 'a') {
            ans += str.charAt(i);
        }
        return removeocc(str, n, i + 1, ans);
    }

    public static void main(String[] args) {
        String str = "vanshahshdasaahsha";
        int n = str.length();
        String ans = "";
        int i = 0;
        System.out.println(removeocc(str, n, i, ans));
    }
}