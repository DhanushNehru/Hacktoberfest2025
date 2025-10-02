import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class decitobi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("DEFINE THE DECIMAL NUMBER");
        int decino = sc.nextInt();
        String ans = "";
        while (decino > 0) {
            int remain = decino % 2;
            ans += remain;
            decino /= 2;
        }
        String realans = "";
        for (int i = ans.length() - 1; i >= 0; i--) {
            realans += ans.charAt(i);
        }
        System.out.println(realans);
    }
}
