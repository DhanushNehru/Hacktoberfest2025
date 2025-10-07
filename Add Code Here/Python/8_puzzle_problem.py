# 8 Puzzle Problem
# Implementation of various search algorithms: BFS, DFS, DLS, IDS, UCS, and ILS
# Each algorithm explores possible puzzle states until it reaches the goal configuration.
# Author: Vidhi Mantry

from collections import deque
import heapq

# Function to display the 3x3 puzzle state in readable form
def print_state(state):
    for i in range(0, 9, 3):
        print(state[i], state[i+1], state[i+2])
    print()

# Function to generate all valid child states from the current state
def get_children(state):
    children = []
    idx = state.index(0)  # Find position of blank (0)
    moves = []

    # Determine possible moves based on blank position
    if idx not in [0, 1, 2]: moves.append(-3)  # Move up
    if idx not in [6, 7, 8]: moves.append(3)   # Move down
    if idx not in [0, 3, 6]: moves.append(-1)  # Move left
    if idx not in [2, 5, 8]: moves.append(1)   # Move right

    # Generate new states by swapping the blank with target position
    for m in moves:
        new_state = state[:]
        new_state[idx], new_state[idx+m] = new_state[idx+m], new_state[idx]
        children.append(new_state)
    return children

# Breadth-First Search (BFS)
def bfs(start, goal):
    print("BFS:")
    q = deque([start])   # Use queue for BFS
    visited = set()      # Track visited states
    while q:
        state = q.popleft()
        if tuple(state) in visited: 
            continue
        visited.add(tuple(state))
        print_state(state)
        if state == goal:
            print("Goal Found!\n")
            return
        for child in get_children(state):
            q.append(child)

# Depth-First Search (DFS)
def dfs(start, goal, limit=20):
    print("DFS:")
    stack = [(start, 0)]  # Stack holds (state, depth)
    visited = set()
    while stack:
        state, depth = stack.pop()
        if tuple(state) in visited: 
            continue
        visited.add(tuple(state))
        print_state(state)
        if state == goal:
            print("Goal Found!\n")
            return
        # Only expand if within depth limit
        if depth < limit:
            for child in get_children(state):
                stack.append((child, depth+1))

# Depth-Limited Search (DLS)
def dls(start, goal, limit=10):
    print(f"DLS (limit={limit}):")
    def recurse(state, depth):
        print_state(state)
        if state == goal:
            print("Goal Found!\n")
            return True
        if depth >= limit:
            return False
        for child in get_children(state):
            if recurse(child, depth+1):
                return True
        return False
    recurse(start, 0)

# Iterative Deepening Search (IDS)
def ids(start, goal, max_depth=10):
    print("IDS:")
    for limit in range(max_depth+1):
        print(f"Depth {limit}")
        if dls(start, goal, limit=limit):
            return

# Heuristic: counts how many tiles are misplaced
def misplaced_tiles(state, goal):
    return sum(1 for i in range(9) if state[i] != goal[i] and state[i] != 0)

# Uniform Cost Search (UCS)
def ucs(start, goal):
    print("UCS:")
    pq = [(misplaced_tiles(start, goal), start)]  # Priority queue (cost, state)
    visited = set()
    while pq:
        cost, state = heapq.heappop(pq)
        if tuple(state) in visited:
            continue
        visited.add(tuple(state))
        print("Cost:", cost)
        print_state(state)
        if state == goal:
            print("Goal Found!\n")
            return
        # Push children with their heuristic cost
        for child in get_children(state):
            heapq.heappush(pq, (misplaced_tiles(child, goal), child))

# Iterative Length Search (ILS) - heuristic-based iterative deepening by cost
def ils(start, goal, max_cost=10):
    print("ILS:")
    for bound in range(max_cost+1):
        print(f"Cost bound {bound}")
        pq = [(misplaced_tiles(start, goal), start)]
        visited = set()
        while pq:
            cost, state = heapq.heappop(pq)
            if cost > bound:
                continue
            if tuple(state) in visited:
                continue
            visited.add(tuple(state))
            print(f"Cost {cost}:")
            print_state(state)
            if state == goal:
                print("Goal Found!\n")
                return
            for child in get_children(state):
                heapq.heappush(pq, (misplaced_tiles(child, goal), child))

# Main execution
if __name__ == "__main__":
    print("Enter start state for 8-puzzle (9 space-separated numbers, 0 for blank):")
    start = list(map(int, input().split()))
    print("Enter goal state for 8-puzzle (9 space-separated numbers, 0 for blank):")
    goal = list(map(int, input().split()))

    # Run all implemented search algorithms sequentially
    bfs(start, goal)
    dfs(start, goal, limit=5)
    dls(start, goal, limit=3)
    ids(start, goal, max_depth=5)
    ucs(start, goal)
    ils(start, goal, max_cost=5)

# Sample Input:
# Start State: 1 2 3 4 0 6 7 5 8
# Goal State:  1 2 3 4 5 6 7 8 0

