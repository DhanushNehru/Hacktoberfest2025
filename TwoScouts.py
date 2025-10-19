# another graph represnetaion problem
# i need to find the minimum number of the two scouts path
# two conditions. 1. a town should not be visited twice. 2. no town should be shared along the path except source and destination
# tricky part is to find total number of towns visited. since both scouts are not simple union of nodes
# the best solution would be to use DFS for 1st scout and BFS for second while restricting access to already travelled nodes

import collections
import sys

# ill set a recussion limit since dfs might go deep
sys.setrecursionlimit(2000)

def solve():
    
    try:
       
        n, m = map(int, sys.stdin.readline().split())
        adj = collections.defaultdict(list)
        for _ in range(m):
            u, v = map(int, sys.stdin.readline().split())
            adj[u].append(v)
            adj[v].append(u)

        start1, start2 = map(int, sys.stdin.readline().split())
        outpost = int(sys.stdin.readline())

    except (IOError, ValueError):
       
        return

    min_total_cost = [float('inf')]

    def bfs_for_bran(forbidden_nodes):
       
        if start2 in forbidden_nodes:
            return None

        q = collections.deque([(start2, 1)])
        visited = set(forbidden_nodes)
        visited.add(start2)

        while q:
            u, length = q.popleft()

            if u == outpost:
                return length

            for v in adj[u]:
                if v not in visited:
                    visited.add(v)
                    q.append((v, length + 1))
        
        return None

    def dfs_for_arin(current_node, path):
        
        
        if current_node == outpost:
            arin_path_len = len(path)
            
            # Bran cannot use any node from Arin's path, except for the outpost.
            forbidden_for_bran = path - {outpost}

            bran_path_len = bfs_for_bran(forbidden_for_bran)
            
            if bran_path_len is not None:
                
                cost = arin_path_len + bran_path_len - 2
                min_total_cost[0] = min(min_total_cost[0], cost)
            return

        for neighbor in adj[current_node]:
            if neighbor not in path:
                path.add(neighbor)
                dfs_for_arin(neighbor, path)
                path.remove(neighbor) # Backtracking to explore other possibilities

    # Starting the search
    initial_path_arin = {start1}
    dfs_for_arin(start1, initial_path_arin)

    if min_total_cost[0] == float('inf'):
        print("Impossible")
    else:
        print(min_total_cost[0])

solve()