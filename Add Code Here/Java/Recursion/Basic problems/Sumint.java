public class Sumint {
    static void Suminte(int n, int ans) {
        if (n == 0) {
            System.out.println("The sum of the given integer is " + ans);
            return;
        }
        int a = n % 10;
        ans += a;
        Suminte(n / 10, ans);
    }

    public static void main(String[] args) {
        int n = 3428;
        int ans = 0;
        Suminte(n, ans);
    }
}