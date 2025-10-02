import java.util.*;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sumofdigit = 0;
        int originalno = n;

        while (n > 0) {
            sumofdigit += (n % 10);
            n = n / 10;
        }
        System.out.println("THE SUM OF DIGIT OF THE NUMBER " + originalno + " IS " + sumofdigit);
    }
}