MOD = 10**9 + 7

def getCount(N, A, Q, T, P):
    def count_non_decreasing_subarrays(arr, L, R):
        count = 0
        length = 1

        for i in range(L, R):
            if arr[i] <= arr[i + 1]:
                length += 1
            else:
                count += (length * (length + 1)) // 2
                length = 1

        count += (length * (length + 1)) // 2
        return count % MOD

    result = 0

    for query in P:
        if query[0] == 1:
            _, L, R, _ = query
            result += count_non_decreasing_subarrays(A, L - 1, R - 1)  # Convert 1-based to 0-based index
            result %= MOD
        elif query[0] == 2:
            _, L, R, X = query
            for i in range(L - 1, R):  # Convert 1-based to 0-based index
                A[i] = X

    return result

import sys

def main():
    input = sys.stdin.read
    data = input().split()
    
    index = 0
    N = int(data[index])
    index += 1
    A = []
    for _ in range(N):
        A.append(int(data[index]))
        index += 1
    Q = int(data[index])
    index += 1
    T = int(data[index])
    index += 1
    
    P = []
    for _ in range(Q):
        query = list(map(int, data[index:index+T]))
        P.append(query)
        index += T

    result = getCount(N, A, Q, T, P)
    print(result)

if __name__ == "__main__":
    main()
