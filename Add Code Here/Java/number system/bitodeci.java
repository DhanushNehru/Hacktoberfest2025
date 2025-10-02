import java.lang.reflect.Array;
import java.util.Scanner;

public class bitodeci {
    public static void main(String[] args) {
        int ans = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("DEFINE THE BINARY NUMBER");
        int binaryno = sc.nextInt();
        int power = 1;// because 2^0 == 1;
        while (binaryno > 0) {
            int unitdigit = binaryno % 10;
            ans += (unitdigit * power);
            binaryno /= 10;
            power *= 2;
        }
        System.out.println("THE DECIMAL OF THE GIVEN BINARY IS " + ans);
    }
}
