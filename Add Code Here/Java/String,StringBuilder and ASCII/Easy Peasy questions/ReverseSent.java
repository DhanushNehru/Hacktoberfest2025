import java.util.Scanner;

public class ReverseSent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "I live in jabalpur";
        String ans = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char currchar = str.charAt(i);
            if (currchar != ' ') {
                sb.append(currchar);
            } else {
                String srt = sb.reverse().toString();
                ans += srt;
                ans += " ";
                sb.delete(0, sb.length());
            }
        } // till this loop we have printed 'i live in' in reverse but jabalpur is not
          // printed because words had to be printed when currchar == space but we had'nt
          // declared space after last words that is why we would print those last words
          // at last
        String lastwords = sb.reverse().toString();// we have printed those last words here
        System.out.println(ans + lastwords);// ...................
    }
}
