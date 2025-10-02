public class Sum1 {
    static int sumFirstn(int n) {
        if (n == 0) {
            return 0;
        }
        return sumFirstn(n - 1) + n;
    }

    public static void main(String[] args) {
        int n = 5;
        int a = sumFirstn(n);
        System.out.println(a);
    }
}
