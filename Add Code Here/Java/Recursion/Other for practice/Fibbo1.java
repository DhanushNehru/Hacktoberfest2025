public class Fibbo1 {
    static int Fibboreturner(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int a = Fibboreturner(n - 1) + Fibboreturner(n - 2);
        return a;
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println(Fibboreturner(n - 1));
    }
}
