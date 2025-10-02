public class Compress {
    public static void main(String[] args) {
        String str = "aaabbbbccdddeeeeee";
        StringBuilder ans = new StringBuilder();
        ans.append(str.charAt(0));
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char currchar = str.charAt(i);
            char prevchar = str.charAt(i - 1);
            if (currchar != prevchar) {
                prevchar = currchar;
                if (count > 1) {
                    ans.append(count);
                }
                ans.append(currchar);
                count = 1;
            } else {
                count++;
            }
        }
        System.out.println(ans.append(count));
    }
}