import java.util.*;

public class alternatingrec {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("DEFINE NO. OF ROWS");
        int n = sc.nextInt();
        System.out.println("DEFINE NO. OF COLUMNS");
        int r = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                if ((i + j) % 2 == 0)
                    System.out.print("1 ");
                else
                    System.out.print("2 ");
            }
            System.out.println();
        }
    }
}
