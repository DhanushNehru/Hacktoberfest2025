public class Prrm2 {
    static void Printrevn(int n) {
        if (n == 1) {
            System.out.println(n);
            return;
        }
        System.out.println(n);
        Printrevn(n - 1);
    }

    public static void main(String[] args) {
        int n = 10;
        Printrevn(n);
    }
}
