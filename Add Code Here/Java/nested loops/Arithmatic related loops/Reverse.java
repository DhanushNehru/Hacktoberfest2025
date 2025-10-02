import java.util.*;

import javax.print.DocFlavor.STRING;

public class Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int originalno = n;
        String rev = "";
        while (n > 0) {
            rev += (n % 10);
            n = n / 10;
        }
        System.out.println("THE REVERSAL OF THE GIVEN NUMBER " + originalno + " is " + rev);
    }
}
