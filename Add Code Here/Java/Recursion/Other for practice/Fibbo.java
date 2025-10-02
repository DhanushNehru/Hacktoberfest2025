public class Fibbo {// This returns fibonacci series...............................
    static int Fibbonaci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int prev = Fibbonaci(n - 1);
        int prevprev = Fibbonaci(n - 2);
        int ans = prev + prevprev;
        return ans;
    }

    public static void main(String[] args) {
        int n = 7;
        for (int i = 0; i < n; i++)
            System.out.println(Fibbonaci(i));
    }
}
