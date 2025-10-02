import java.util.Scanner;

public class Gcd {
    static int Gcdret(int x, int y) {
        if (y == 0) {
            return x;
        }
        return Gcdret(y, x % y);// hcf or gcd
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write greater no.");
        int x = sc.nextInt();
        System.out.println("Write smaller no.");
        int y = sc.nextInt();
        int a = Gcdret(x, y);
        System.out.println("GCD is " + a);
        System.out.println("LCM is " + (x * y) / a);// lcm
    }
}
