import java.util.*;

public class Nfact {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int originalno = n;
        int fact = 1;
        for (int i = n; i >= 1; i--) {
            fact *= i;
        }
        System.out.println("THE FACTORIAL OF THE FIRST " + originalno + " is " + fact);
    }
}
