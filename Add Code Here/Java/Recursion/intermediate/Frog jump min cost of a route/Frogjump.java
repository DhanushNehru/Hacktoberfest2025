public class Frogjump {
    static int jumpfrog(int[] h, int n, int idx) {
        if (idx == n - 1) {
            return 0;
        }
        int op1 = jumpfrog(h, n, idx + 1) + Math.abs(h[idx + 1] - h[idx]);/*
                                                                           * this will add the value of self work and
                                                                           * recursive or faith work which
                                                                           * has been done by the recursion
                                                                           */
        if (idx == n - 2) {
            return op1;
        }
        int op2 = jumpfrog(h, n, idx + 2) + Math.abs(h[idx + 2] - h[idx]);
        int Mincost = Math.min(op1, op2);
        return Mincost;
    }

    public static void main(String[] args) {
        int[] h = { 10, 30, 40, 20 };
        int n = h.length;
        int idx = 0;
        int ans = jumpfrog(h, n, idx);
        System.out.println("The minimum cost for jumping to the last stone is: " + ans);
    }
}