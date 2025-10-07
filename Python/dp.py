def dfs(i, j, m, dp):
    if dp[i][j]: return dp[i][j]
    n, p = len(m), len(m[0])
    ans = 1
    for x, y in [(1,0),(-1,0),(0,1),(0,-1)]:
        ni, nj = i + x, j + y
        if 0 <= ni < n and 0 <= nj < p and m[ni][nj] > m[i][j]:
            ans = max(ans, 1 + dfs(ni, nj, m, dp))
    dp[i][j] = ans
    return ans

def longestIncreasingPath(m):
    n, p = len(m), len(m[0])
    dp = [[0]*p for _ in range(n)]
    res = 0
    for i in range(n):
        for j in range(p):
            res = max(res, dfs(i, j, m, dp))
    return res

n, p = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]
print(longestIncreasingPath(matrix))
