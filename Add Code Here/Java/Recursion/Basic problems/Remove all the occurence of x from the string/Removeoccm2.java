public class Removeoccm2 {
    static String removeocc(String str, int i, int n) {
        if (i == n) {
            return "";
        }
        String smallans = removeocc(str, i + 1, n);
        char currchar = str.charAt(i);
        if (currchar != 'a') {
            return currchar + smallans;
        } else {
            return smallans;
        }
    }

    public static void main(String[] args) {
        String str = "vnashhfafjasdffa";
        int i = 0;
        int n = str.length();
        System.out.println(removeocc(str, i, n));
    }
}
