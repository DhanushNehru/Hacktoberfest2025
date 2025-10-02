import java.util.Scanner;

//This method will print first n natural numbers............
public class Firstn {
    static void Printnatural(int n) {
        if (n == 1) {
            System.out.println(1);
            return;
        }
        Printnatural(n - 1);
        System.out.println(n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the n");
        int n = sc.nextInt();
        Printnatural(n);
    }
}