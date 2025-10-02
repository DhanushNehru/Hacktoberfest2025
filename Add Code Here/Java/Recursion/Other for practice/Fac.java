public class Fac {
    static void Factorialprr(int n, int a) {
        if (n == 1) {
            a *= n;
            System.out.println(a);
            return;
        }
        a *= n;
        Factorialprr(n - 1, a);
    }

    public static void main(String[] args) {
        int n = 5;
        int a = 1;
        Factorialprr(n, a);
    }
}
