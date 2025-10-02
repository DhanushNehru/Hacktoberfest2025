public class Palindrom {
    public static void main(String[] args) {
        String str = "abcba";
        int count = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String strsub = str.substring(i, j);
                StringBuilder strsub1 = new StringBuilder(strsub);
                String gtr = strsub1.reverse().toString();
                if (strsub.equals(gtr)) {
                    System.out.print(strsub + " ");
                    count++;
                } else {
                    continue;
                }
            }
        }
        System.out.println("The possible palindroms in the substrings are: " + count);
    }
}