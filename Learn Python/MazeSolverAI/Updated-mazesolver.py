import pygame
import math
import random
import time
from queue import PriorityQueue

# Initialize Pygame
pygame.init()
pygame.font.init()

# Window dimensions
WIDTH = 600
HEIGHT = WIDTH + 100  # extra space for buttons
ROWS = 30
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Maze Solver AI - A* Pathfinding")

# Colors
WHITE   = (255,255,255)
GREY    = (128,128,128)
BLACK   = (  0,  0,  0)
ORANGE  = (255,165,  0)
PURPLE  = (160, 32,240)
GREEN   = (  0,255,  0)
RED     = (255,  0,  0)
BLUE    = ( 64,224,208)

class Spot:
    def __init__(self, row, col, width):
        self.row, self.col = row, col
        self.x = col * width
        self.y = row * width
        self.color = WHITE
        self.neighbors = []
        self.width = width

    def get_pos(self):
        return self.row, self.col

    def is_barrier(self):
        return self.color == BLACK

    def reset(self):
        self.color = WHITE

    def make_start(self):
        self.color = ORANGE

    def make_end(self):
        self.color = PURPLE

    def make_barrier(self):
        self.color = BLACK

    def make_path(self):
        self.color = (255, 255, 0)

    def make_open(self):
        self.color = (174, 198, 255)

    def make_closed(self):
        self.color = (255, 179, 179)

    def draw(self, win):
        pygame.draw.rect(win, self.color, (self.x, self.y, self.width, self.width))

    def update_neighbors(self, grid):
        self.neighbors = []
        if self.row < ROWS-1 and not grid[self.row+1][self.col].is_barrier():
            self.neighbors.append(grid[self.row+1][self.col])
        if self.row > 0 and not grid[self.row-1][self.col].is_barrier():
            self.neighbors.append(grid[self.row-1][self.col])
        if self.col < ROWS-1 and not grid[self.row][self.col+1].is_barrier():
            self.neighbors.append(grid[self.row][self.col+1])
        if self.col > 0 and not grid[self.row][self.col-1].is_barrier():
            self.neighbors.append(grid[self.row][self.col-1])

class MazeAgent:
    def __init__(self, grid, start, end):
        self.grid = grid
        self.start = start
        self.end = end
        self.path_found = False

    def set_goal(self, end):
        self.end = end

    def update_environment(self, grid):
        self.grid = grid

    def perceive(self):
        visible_cells = []
        for row in self.grid:
            for spot in row:
                if random.random() < 0.9:
                    visible_cells.append(spot)
        return visible_cells

    def act(self, draw_callback):
        for row in self.grid:
            for spot in row:
                spot.update_neighbors(self.grid)
        self.path_found = algorithm(draw_callback, self.grid, self.start, self.end)
        return self.path_found

    def plan(self, draw, visible_cells):
        pass

def h(p1, p2):
    return abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])

def reconstruct_path(came_from, current, draw, start):
    while current in came_from:
        current = came_from[current]
        if current != start:
            current.make_path()
        draw()

def algorithm(draw, grid, start, end):
    count = 0
    open_set = PriorityQueue()
    open_set.put((0, count, start))
    came_from = {}

    g_score = {spot: float('inf') for row in grid for spot in row}
    g_score[start] = 0
    f_score = {spot: float('inf') for row in grid for spot in row}
    f_score[start] = h(start.get_pos(), end.get_pos())

    open_set_hash = {start}

    while not open_set.empty():
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                return False

        current = open_set.get()[2]
        open_set_hash.remove(current)

        if current == end:
            reconstruct_path(came_from, end, draw, start)
            end.make_end()
            return True

        for neighbor in current.neighbors:
            temp_g = g_score[current] + 1
            if temp_g < g_score[neighbor]:
                came_from[neighbor] = current
                g_score[neighbor] = temp_g
                f_score[neighbor] = temp_g + h(neighbor.get_pos(), end.get_pos())
                if neighbor not in open_set_hash:
                    count += 1
                    open_set.put((f_score[neighbor], count, neighbor))
                    open_set_hash.add(neighbor)
                    neighbor.make_open()

        draw()
        if current != start:
            current.make_closed()

    return False

def make_grid(rows, width):
    gap = width // rows
    return [[Spot(r, c, gap) for c in range(rows)] for r in range(rows)]

def draw_grid_lines(win, rows, width):
    gap = width // rows
    for i in range(rows):
        pygame.draw.line(win, GREY, (0, i*gap), (width, i*gap))
        pygame.draw.line(win, GREY, (i*gap, 0), (i*gap, width))

def draw(win, grid, rows, width):
    win.fill(WHITE)
    for row in grid:
        for spot in row:
            spot.draw(win)
    draw_grid_lines(win, rows, width)

    # Title
    font = pygame.font.SysFont('arial', 30)
    text = font.render("Maze Solver AI", True, BLACK)
    win.blit(text, (width//2 - text.get_width()//2, 10))

    # Buttons
    bw, bh, sp = 120, 50, 40
    total = 2*bw + sp
    x0 = (width - total)//2
    y0 = width + 20

    pygame.draw.rect(win, RED, (x0, y0, bw, bh))
    rt = font.render("Reset", True, WHITE)
    win.blit(rt, (x0 + (bw-rt.get_width())//2, y0 + (bh-rt.get_height())//2))

    qx = x0 + bw + sp
    pygame.draw.rect(win, BLUE, (qx, y0, bw, bh))
    qt = font.render("Quit", True, WHITE)
    win.blit(qt, (qx + (bw-qt.get_width())//2, y0 + (bh-qt.get_height())//2))

    pygame.display.update()

def get_clicked_pos(pos, rows, width):
    x, y = pos
    gap = width // rows
    if x < 0 or x >= width or y < 0 or y >= width:
        return None, None
    row = y // gap
    col = x // gap
    return row, col

def draw_message(win, width, message):
    font = pygame.font.SysFont('arial', 30)
    text = font.render(message, True, RED)
    win.blit(text, (width//2 - text.get_width()//2, WIDTH//2 - text.get_height()//2))
    pygame.display.update()

def main(win, width):
    pathfinding_done = False
    pathfinding_success = False

    grid = make_grid(ROWS, width)
    start, end = None, None
    agent = None

    bw, bh, sp = 120, 50, 40
    total = 2 * bw + sp
    x0 = (width - total) // 2
    y0 = width + 20

    run = True
    while run:
        draw(win, grid, ROWS, width)

        if pathfinding_done and not pathfinding_success:
            draw_message(win, width, "No path found! Barriers are blocking the way.")

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False

            elif event.type == pygame.MOUSEBUTTONDOWN:
                mx, my = pygame.mouse.get_pos()

                # Reset button
                if x0 < mx < x0 + bw and y0 < my < y0 + bh:
                    grid = make_grid(ROWS, width)
                    start = end = None
                    agent = None
                    pathfinding_done = False
                    pathfinding_success = False
                    continue

                # Quit button
                if x0 + bw + sp < mx < x0 + 2 * bw + sp and y0 < my < y0 + bh:
                    run = False
                    continue

                row, col = get_clicked_pos((mx, my), ROWS, width)
                if row is None:
                    continue
                spot = grid[row][col]

                if not pathfinding_done:  # prevent changes after pathfinding
                    if not start and spot != end:
                        start = spot
                        start.make_start()
                    elif not end and spot != start:
                        end = spot
                        end.make_end()
                    elif spot not in (start, end) and not spot.is_barrier():
                        spot.make_barrier()

            elif event.type == pygame.MOUSEMOTION and pygame.mouse.get_pressed()[0]:
                if not pathfinding_done:
                    mx, my = pygame.mouse.get_pos()
                    row, col = get_clicked_pos((mx, my), ROWS, width)
                    if row is None:
                        continue
                    spot = grid[row][col]
                    if spot not in (start, end) and not spot.is_barrier():
                        spot.make_barrier()

            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE and start and end and not pathfinding_done:
                    for r in grid:
                        for spot in r:
                            spot.update_neighbors(grid)
                    agent = MazeAgent(grid, start, end)
                    pathfinding_success = agent.act(lambda: draw(win, grid, ROWS, width))
                    pathfinding_done = True

                    if not pathfinding_success:
                        draw_message(win, width, "No path found: blocked by barriers!")
                        pygame.time.delay(3000)

                if event.key == pygame.K_c:
                    grid = make_grid(ROWS, width)
                    start = end = None
                    agent = None
                    pathfinding_done = False
                    pathfinding_success = False

        # Spawn barriers ONLY if pathfinding not done yet
        if start and end and not pathfinding_done and random.random() < 0.05:
            x = random.randint(0, ROWS - 1)
            y = random.randint(0, ROWS - 1)
            spot = grid[x][y]
            if spot not in (start, end) and not spot.is_barrier():
                spot.make_barrier()

    pygame.quit()

main(WIN, WIDTH)
