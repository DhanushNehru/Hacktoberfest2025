def minCut(s):
    n = len(s)
    dp = [0]*n
    isPal = [[False]*n for _ in range(n)]
    for i in range(n-1,-1,-1):
        min_cut = n
        for j in range(i,n):
            if s[i]==s[j] and (j-i<=1 or isPal[i+1][j-1]):
                isPal[i][j] = True
                min_cut = 0 if j==n-1 else min(min_cut, 1+dp[j+1])
        dp[i] = min_cut
    return dp[0]
