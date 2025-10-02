import java.util.Scanner;

public class PoorToggle {// this one is poor performance method..........not
                         // recommended...................
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);// currcharacter
            if (x == ' ') {
                continue;
            }
            int currchar = (int) x;// converts the currcharacter into ascii code
            if (currchar < 97 && currchar > 58) {
                currchar += 32;
            } else if (currchar >= 96) {
                currchar -= 32;
            } else {
                continue;
            }
            char anschar = (char) currchar;// converts the answer ascii code into chararcter
            str = str.substring(0, i) + anschar + str.substring(i + 1);
        }
        System.out.println(str);
    }
}
