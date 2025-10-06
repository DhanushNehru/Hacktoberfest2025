def solve(arr,N):
  N_shape = []
  for i in range(N-1,-1,-1):
    N_shape.append(arr[i][0])
  for i in range(1,N):
    N_shape.append(arr[i][i])
  for i in range(N-2,-1,-1):
    N_shape.append(arr[i][N-1])
  for i in N_shape:
    print(i, end = ' ')
  print()
