public class Raisepowerm2 {
    static int Raisetopow(int n, int b) {
        if (b == 0) {
            return 1;
        }
        int a = Raisetopow(n, b / 2);
        if (b % 2 == 0) {
            return a * a;
        } else {
            return a * a * n;
        }
    }

    public static void main(String[] args) {
        int n = 2;
        int b = 5;
        int c = Raisetopow(n, b);
        System.out.println(c);
    }
}
