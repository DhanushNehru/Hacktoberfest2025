import java.util.*;

public class PrGCD {
    public static int printGcd(int divisor, int divident) {
        while (divident % divisor != 0) {
            int rem = divident % divisor;
            divident = divisor;
            divisor = rem;
        }
        return divisor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Print 2 numbers");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int divisor = Math.min(x, y);
        int divident = Math.max(x, y);
        System.out.println(printGcd(divisor, divident));
    }
}
