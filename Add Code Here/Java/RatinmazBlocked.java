public class RatinmazBlocked {
    public static void ratin(int[][] arr, int i, int j, int n, int m, String ans) {
        if (i > n || j > m) {
            return;
        }
        if (i < 0 || j < 0)
            return;
        if (arr[i][j] == 0)
            return;
        if (i == n && j == m) {
            System.out.println(ans);
            return;
        }
        arr[i][j] = 0;
        ratin(arr, i, j + 1, n, m, ans + 'R');
        ratin(arr, i + 1, j, n, m, ans + 'D');
        ratin(arr, i, j - 1, n, m, ans + 'L');
        ratin(arr, i - 1, j, n, m, ans + 'U');
        arr[i][j] = 1;
    }

    public static void Rat(int[][] arr) {
        ratin(arr, 0, 0, arr.length - 1, arr[0].length - 1, "");
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 0, 1, 1 },
                { 1, 1, 1, 1 } };
        Rat(arr);
    }
}
