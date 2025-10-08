# use the input-file to find a target work and then run this code, this code allows for multidirectional search
# to run this code, use python3 multidirectinal-word.py



def count_xmas(grid):
    rows = len(grid)
    cols = len(grid[0])
    target = "XMAS"
    count = 0
    
    directions = [
        (0, 1),   # right
        (0, -1),  # left
        (1, 0),   # down
        (-1, 0),  # up
        (1, 1),   # down-right
        (1, -1),  # down-left
        (-1, 1),  # up-right
        (-1, -1)  # up-left
    ]
    
    def check_direction(row, col, dx, dy):

        for i in range(len(target)):
            new_row = row + i * dx
            new_col = col + i * dy
            
            # Check bounds
            if new_row < 0 or new_row >= rows or new_col < 0 or new_col >= cols:
                return False
            
            # Check if character matches
            if grid[new_row][new_col] != target[i]:
                return False
        
        return True
    
    # Check every position in the grid
    for row in range(rows):
        for col in range(cols):
            # Try all 8 directions from this position
            for dx, dy in directions:
                if check_direction(row, col, dx, dy):
                    count += 1
    
    return count


def main():

    with open('input-file', 'r') as f:
        grid = [line.strip() for line in f.readlines()]
    
    # Count all occurrences of XMAS
    result = count_xmas(grid)
    
    print(f"Total occurrences of XMAS: {result}")


if __name__ == "__main__":
    main()
