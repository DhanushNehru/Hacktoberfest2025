public class Raisepower {
    static int Raisetopow(int n, int b) {
        if (b == 0) {
            return 1;
        }
        int a = Raisetopow(n, b - 1);
        return a *= n;// or a*n;
    }

    public static void main(String[] args) {
        int n = 2;
        int b = 3;
        int c = Raisetopow(n, b);
        System.out.println(c);
    }
}