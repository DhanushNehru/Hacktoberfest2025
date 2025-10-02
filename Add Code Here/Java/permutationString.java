public class permutationString {
    public static void Printperm(String str, String ans) {// Prints the diffrent arrangement of the characters..........
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);// current character......
            String left = str.substring(0, i);// left remain string before the current character......
            String right = str.substring(i + 1);// right remain string after the current character.......
            Printperm(left + right, ans + ch);
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        Printperm(str, "");
    }
}
