import java.util.*;

public class Main {
    static int dfs(int i, int j, int[][] m, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        int n = m.length, p = m[0].length, ans = 1;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && y >= 0 && x < n && y < p && m[x][y] > m[i][j])
                ans = Math.max(ans, 1 + dfs(x, y, m, dp));
        }
        return dp[i][j] = ans;
    }

    static int longestIncreasingPath(int[][] m) {
        int n = m.length, p = m[0].length, res = 0;
        int[][] dp = new int[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                res = Math.max(res, dfs(i, j, m, dp));
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), p = sc.nextInt();
        int[][] matrix = new int[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                matrix[i][j] = sc.nextInt();
        System.out.println(longestIncreasingPath(matrix));
    }
}
