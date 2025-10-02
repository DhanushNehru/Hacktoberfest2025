import java.util.*;

public class rectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("HOW MANY ROWS DO YOU WANT");
        int r = sc.nextInt();
        System.out.println("HOW MANY COLUMNS DO YOU WANT");
        int c = sc.nextInt();
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
