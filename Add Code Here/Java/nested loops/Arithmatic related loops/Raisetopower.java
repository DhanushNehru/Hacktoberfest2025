import java.util.Scanner;

public class Raisetopower {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("DEFINE THE BASE");
        int a = sc.nextInt();
        int originala = a;
        System.out.println("DEFINE THE POWER");
        int b = sc.nextInt();
        for (int i = 1; i < b; i++) {
            a *= originala;
        }
        System.out.println(a);
    }
}