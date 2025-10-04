def colorful_paths(grid):
    rows, cols = len(grid), len(grid[0])
    result = []

    def dfs(r, c, path, colors):
        if r < 0 or c < 0 or r >= rows or c >= cols:
            return
        color = grid[r][c]
        if color in colors:
            return
        path.append((r, c))
        colors.add(color)
        # If reached the bottom-right cell, save the path
        if r == rows - 1 and c == cols - 1:
            result.append(path.copy())
        else:
            # Move in 4 directions
            dfs(r + 1, c, path, colors)
            dfs(r, c + 1, path, colors)
            dfs(r - 1, c, path, colors)
            dfs(r, c - 1, path, colors)
        path.pop()
        colors.remove(color)

    dfs(0, 0, [], set())
    return result

# Example usage:
grid = [
    ['red', 'blue', 'green'],
    ['yellow', 'red', 'purple'],
    ['black', 'white', 'green']
]

paths = colorful_paths(grid)
print(f"Found {len(paths)} unique colorful paths!")
for p in paths:
    print(p)
