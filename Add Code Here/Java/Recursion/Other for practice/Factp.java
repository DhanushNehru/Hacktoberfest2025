public class Factp {
    static int fact(int n) {
        if (n == 0)
            return 1;// Base Case
        int a = fact(n - 1);// Assumption
        return n * a;// Self Work
    }

    public static void main(String[] args) {
        int x = 5;
        System.out.println(fact(x));
    }
}
