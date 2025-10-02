public class Prrm1 {
    static void Printrevn(int n, int a) {
        if (a == n) {// Base Case
            System.out.println(n);
            return;
        }
        Printrevn(n, a + 1);// Recursive Case or Smaller problem
        System.out.println(a);// Self work
    }

    public static void main(String[] args) {
        int n = 10;
        int a = 1;
        Printrevn(n, a);
    }
}
