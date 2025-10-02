import java.util.*;

public class Numpira {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        for (int i = 1; i <= r - 1; i++) {
            for (int j = 1; j < r - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if (i == 2 && k == 2 * i - i) {
                    System.out.print(" ");
                } else if (i == 3 && (k == 2 * i - 2 || k == 2 * i - i || k == 2 * i - i - 1)) {
                    System.out.print(" ");
                } else
                    System.out.print(i);
            }
            System.out.println();
        }
    }

}
