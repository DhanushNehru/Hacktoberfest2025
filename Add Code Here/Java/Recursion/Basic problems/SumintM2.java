public class SumintM2 {
    static int Suminte(int n) {
        if (n >= 0 && n <= 9)
            return n;
        return Suminte(n / 10) + (n % 10);
        // int dig = Suminte(n / 10);
        // return dig + (n % 10);
    }

    public static void main(String[] args) {
        int n = 1234;
        int ans = Suminte(n);
        System.out.println(ans);
    }
}
