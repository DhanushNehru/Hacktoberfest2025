import java.util.Scanner;

public class Alternatingsum {
    static int alternatingsum(int n) {
        if (n == 0) {
            return 0;
        }
        if (n % 2 == 0) {
            return alternatingsum(n - 1) - n;
        } else {
            return alternatingsum(n - 1) + n;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the number of which you want alternating sum");
        int n = sc.nextInt();
        int a = alternatingsum(n);
        System.out.println(a);
    }
}
