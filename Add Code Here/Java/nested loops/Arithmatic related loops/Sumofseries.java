import java.util.*;

public class Sumofseries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sumofseries = 0;
        int originalno = n;
        for (int i = 1; i <= n; i++) {
            if ((i % 2) == 0) {
                sumofseries -= i;
            } else {
                sumofseries += i;
            }
        }
        System.out.println("THE SUM OF SERIES OF THE GIVEN NUMBER " + originalno + " is " + sumofseries);
    }
}
