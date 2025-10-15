class Solution {
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix==null || matrix.length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxLen = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                maxLen = Math.max(maxLen, dfs(matrix, dp, i, j));
            }
        }
        return maxLen;
    }
    
    private int dfs(int[][] mat, int[][] dp, int i, int j){
        if(dp[i][j]!=0) return dp[i][j];
        int max = 1;
        for(int[] d: dirs){
            int x = i + d[0], y = j + d[1];
            if(x>=0 && x<mat.length && y>=0 && y<mat[0].length && mat[x][y]>mat[i][j]){
                max = Math.max(max, 1 + dfs(mat, dp, x, y));
            }
        }
        dp[i][j]=max;
        return max;
    }
}
