import java.util.Scanner;

public class Multiples {
    static void Multiple(int n, int b) {
        if (b == 0) {
            return;
        }
        Multiple(n, b - 1);
        System.out.println(n * b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Of which number you want multiples");
        int n = sc.nextInt();
        System.out.println("How many multiples you want");
        int b = sc.nextInt();
        Multiple(n, b);
    }
}
