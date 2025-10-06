
t = int(input())
for _ in range(t):
    n,m = map(int,input().split())
    matrix = []
    for i in range(n):
        x = list(map(int,input().split()))
        matrix.append(x)
    m2 = [[0 for _ in range(m)] for _ in range(n)]

    ma= 0 
    for i in range(n):
        for j in range(m):
            if i==0 or j==0:
                #print(i,j)
                m2[i][j] = matrix[i][j]
                if ma<m2[i][j]:
                    ma  = m2[i][j]
            elif matrix[i][j]==0:
                m2[i][j] =0
            else:
                m2[i][j] = matrix[i][j] + min(m2[i-1][j],m2[i-1][j-1],m2[i][j-1])
                if m2[i][j] >ma:
                    ma = m2[i][j]
    print(ma*ma)
