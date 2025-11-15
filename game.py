import pygame
import math
import heapq

pygame.init()
screen = pygame.display.set_mode((800, 800))
clock = pygame.time.Clock()

cell_size = 40
ROWS = int(800 / cell_size)
COLS = int(800 / cell_size)

# Colors
BLUE = (50, 100, 200)
GREEN = (0, 180, 80)
RED = (220, 50, 50)
BORDER = (20, 20, 60)
BG = (30, 30, 30)
TEXT = (240, 240, 240)
WALL = (40, 40, 40)

# Movement settings
DIAGONALS = True  # set False for 4-directional movement only

class Cell:
    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.cost = 1
        self.heuristic = 0
        self.color = BLUE
        self.is_wall = False
        # Top-left pixel position (x, y)
        self.position = (col * cell_size, row * cell_size)
        self.center = (self.position[0] + cell_size // 2, self.position[1] + cell_size // 2)

    def calculate_heuristic(self, end_row, end_col):
        # Walls have infinite heuristic (treated as non-traversable)
        if self.is_wall:
            self.heuristic = float('inf')
        else:
            # Manhattan distance in grid coordinates
            self.heuristic = abs(self.row - end_row) + abs(self.col - end_col)

 
 

grid = []
for r in range(ROWS):
    grid.append([])
    for c in range(COLS):
        grid[r].append(Cell(r, c))

def in_bounds(r, c):
    return 0 <= r < ROWS and 0 <= c < COLS

def get_neighbors(r, c):
    # 4-directional neighbors
    dirs4 = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    dirs8 = dirs4 + [(-1, -1), (-1, 1), (1, -1), (1, 1)]
    dirs = dirs8 if DIAGONALS else dirs4
    out = []
    for dr, dc in dirs:
        nr, nc = r + dr, c + dc
        if in_bounds(nr, nc) and not grid[nr][nc].is_wall:
            out.append((nr, nc))
    return out

def recompute_heuristics(end_pos):
    er, ec = end_pos
    for rr in range(ROWS):
        for cc in range(COLS):
            grid[rr][cc].calculate_heuristic(er, ec)

def clear_path_colors(start_pos, end_pos):
    for rr in range(ROWS):
        for cc in range(COLS):
            if (rr, cc) == start_pos:
                grid[rr][cc].color = GREEN
            elif (rr, cc) == end_pos:
                grid[rr][cc].color = RED
            elif grid[rr][cc].is_wall:
                # keep walls dark
                grid[rr][cc].color = WALL
            else:
                grid[rr][cc].color = BLUE

def greedy_best_first(start_pos, end_pos):
    # Use a priority queue ordered by heuristic only
    sr, sc = start_pos
    er, ec = end_pos
    pq = []
    heapq.heappush(pq, (grid[sr][sc].heuristic, (sr, sc)))
    came_from = {}
    visited = set()

    while pq:
        _, (cr, cc) = heapq.heappop(pq)
        if (cr, cc) in visited:
            continue
        visited.add((cr, cc))

        if (cr, cc) == (er, ec):
            # Reconstruct path
            path = [(er, ec)]
            cur = (er, ec)
            while cur in came_from:
                cur = came_from[cur]
                path.append(cur)
            path.reverse()
            return path

        for nr, nc in get_neighbors(cr, cc):
            if (nr, nc) not in visited:
                if (nr, nc) not in came_from:
                    came_from[(nr, nc)] = (cr, cc)
                heapq.heappush(pq, (grid[nr][nc].heuristic, (nr, nc)))

    return None

def draw_grid(start, end):
    screen.fill(BG)
    for row_idx, row_vals in enumerate(grid):
        for col_idx, cell in enumerate(row_vals):
            rect = pygame.Rect(col_idx * cell_size, row_idx * cell_size, cell_size, cell_size)
            # Determine color priority: walls > start/end > cell.color
            fill_color = cell.color
            if cell.is_wall:
                fill_color = WALL
            if start is not None and (row_idx, col_idx) == start:
                fill_color = GREEN
            if end is not None and (row_idx, col_idx) == end:
                fill_color = RED

            pygame.draw.rect(screen, fill_color, rect)
            pygame.draw.rect(screen, BORDER, rect, 2)
            # draw coordinates text
            font = pygame.font.SysFont(None, 12)
            # Show heuristic (∞ for walls)
            if math.isinf(cell.heuristic):
                display = "∞"
            else:
                display = str(cell.heuristic)
            text = font.render(display, True, TEXT)
            text_rect = text.get_rect(center=rect.center)
            screen.blit(text, text_rect)

    pygame.display.flip()

def get_cell_from_mouse(pos):
    col = pos[0] // cell_size
    row = pos[1] // cell_size
    if 0 <= row < len(grid) and 0 <= col < len(grid[row]):
        return row, col
    return None

running = True
start = None
end = None
h = None
distance_grid = None
while running:
    draw_grid(start, end )
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
            cell = get_cell_from_mouse(event.pos)
            if not cell:
                continue
            r_idx, c_idx = cell
            print(f"Clicked cell ({r_idx}, {c_idx})")

            # set start first, then end (only if different from start)
            if start is None:
                start = (r_idx, c_idx)
                print(f"Start set to: {start}")
                grid[start[0]][start[1]].color = GREEN
            if end is None and cell != start:
                end = (r_idx, c_idx)
                print(f"End set to: {end}")
                grid[end[0]][end[1]].color = RED
                h = None  # force heuristics recompute when end changes
            # else:
            #     grid[r_idx][c_idx].color = RED
            #     grid[r_idx][c_idx].heuristic = 100
        # Right-click toggles wall on a cell (cannot wall start or end)
        elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 3:
            cell = get_cell_from_mouse(event.pos)
            if not cell:
                continue
            r_idx, c_idx = cell
            if start is not None and cell == start:
                continue
            if end is not None and cell == end:
                continue
            target = grid[r_idx][c_idx]
            target.is_wall = not target.is_wall
            # Reset color when removing a wall
            if not target.is_wall:
                target.color = BLUE
            # Force heuristics recompute next time
            h = None
        if event.type == pygame.MOUSEBUTTONDOWN and event.button == 2 and start and end:
            # Recompute heuristics and run greedy best-first search
            recompute_heuristics(end)
            h = True
            clear_path_colors(start, end)
            path = greedy_best_first(start, end)
            if not path:
                print("No path found.")
            else:
                print(f"Path found: {path}")
                for pr, pc in path:
                    if (pr, pc) != start and (pr, pc) != end:
                        grid[pr][pc].color = (150, 150, 250)
    clock.tick(60)

pygame.quit()
