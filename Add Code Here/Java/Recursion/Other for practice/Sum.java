public class Sum {
    static void SumFirstn(int n, int a) {
        if (n == 0) {
            System.out.println(a);
            return;
        }
        a += n;
        SumFirstn(n - 1, a);
    }

    public static void main(String[] args) {
        int n = 5;
        int a = 0;
        SumFirstn(n, a);
    }
}
