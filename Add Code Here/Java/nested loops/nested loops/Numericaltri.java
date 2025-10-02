import java.util.*;

public class Numericaltri {
    public static void main(String[] args) {
        System.out.println("WRITE THE NUMBER");
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        for (int i = 1; i <= r; i++) {
            for (int j = i; j <= r; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
}
