import pygame
import random
from dataclasses import dataclass

# --- Konfigurasi global ---
CELL_SIZE = 25
GRID_COLS = 24
GRID_ROWS = 24
WIDTH  = GRID_COLS * CELL_SIZE
HEIGHT = GRID_ROWS * CELL_SIZE
FPS = 12  # kecepatan awal (snake)

# Warna
BLACK  = (18, 18, 18)
WHITE  = (240, 240, 240)
GREEN  = (56, 199, 109)
RED    = (235, 87, 87)
YELLOW = (252, 196, 25)
GRAY   = (100, 100, 100)

# Arah gerak
UP, DOWN, LEFT, RIGHT = (0, -1), (0, 1), (-1, 0), (1, 0)


@dataclass
class Point:
    x: int
    y: int

    def __add__(self, other):
        return Point(self.x + other[0], self.y + other[1])


class Snake:
    def __init__(self):
        cx, cy = GRID_COLS // 2, GRID_ROWS // 2
        self.segments = [Point(cx, cy), Point(cx - 1, cy), Point(cx - 2, cy)]
        self.direction = RIGHT
        self.grow_pending = 0

    def head(self) -> Point:
        return self.segments[0]

    def set_direction(self, new_dir):
        # Cegah berputar 180 derajat
        if (self.direction[0] + new_dir[0] == 0 and
            self.direction[1] + new_dir[1] == 0):
            return
        self.direction = new_dir

    def move(self):
        new_head = self.head() + self.direction
        self.segments.insert(0, new_head)
        if self.grow_pending > 0:
            self.grow_pending -= 1
        else:
            self.segments.pop()

    def grow(self, n=1):
        self.grow_pending += n

    def hits_self(self) -> bool:
        return self.head() in self.segments[1:]

    def hits_wall(self) -> bool:
        h = self.head()
        return not (0 <= h.x < GRID_COLS and 0 <= h.y < GRID_ROWS)

    def occupies(self, p: Point) -> bool:
        return p in self.segments


class Food:
    def __init__(self, snake: Snake):
        self.pos = self._random_free_position(snake)

    def _random_free_position(self, snake: Snake) -> Point:
        free = [Point(x, y)
                for x in range(GRID_COLS)
                for y in range(GRID_ROWS)
                if not snake.occupies(Point(x, y))]
        return random.choice(free) if free else Point(0, 0)

    def respawn(self, snake: Snake):
        self.pos = self._random_free_position(snake)


class Game:
    def __init__(self):
        pygame.init()
        pygame.display.set_caption("Snake (Panah untuk bergerak, P untuk pause, R untuk restart)")
        self.screen = pygame.display.set_mode((WIDTH, HEIGHT))
        self.clock = pygame.time.Clock()
        self.font = pygame.font.SysFont("consolas", 22)
        self.big_font = pygame.font.SysFont("consolas", 38, bold=True)

        self.reset()

    def reset(self):
        self.snake = Snake()
        self.food = Food(self.snake)
        self.score = 0
        self.game_over = False
        self.paused = False
        self.speed = FPS  # bisa naik saat skor bertambah

    def update(self):
        if self.game_over or self.paused:
            return
        self.snake.move()

        # Cek tabrakan
        if self.snake.hits_wall() or self.snake.hits_self():
            self.game_over = True
            return

        # Makan
        if self.snake.head() == self.food.pos:
            self.snake.grow(1)
            self.score += 1
            self.food.respawn(self.snake)
            # Naikkan kecepatan setiap beberapa poin
            if self.score % 5 == 0:
                self.speed = min(30, self.speed + 1)

    def draw_grid(self):
        # Grid subtle
        for x in range(0, WIDTH, CELL_SIZE):
            pygame.draw.line(self.screen, (28, 28, 28), (x, 0), (x, HEIGHT))
        for y in range(0, HEIGHT, CELL_SIZE):
            pygame.draw.line(self.screen, (28, 28, 28), (0, y), (WIDTH, y))

    def draw(self):
        self.screen.fill(BLACK)
        self.draw_grid()

        # Gambar makanan
        fx, fy = self.food.pos.x * CELL_SIZE, self.food.pos.y * CELL_SIZE
        pygame.draw.rect(self.screen, RED, (fx, fy, CELL_SIZE, CELL_SIZE), border_radius=6)

        # Gambar ular
        for i, seg in enumerate(self.snake.segments):
            sx, sy = seg.x * CELL_SIZE, seg.y * CELL_SIZE
            color = GREEN if i == 0 else (70, 210, 140)
            pygame.draw.rect(self.screen, color, (sx, sy, CELL_SIZE, CELL_SIZE), border_radius=6)

        # Skor
        score_surf = self.font.render(f"Score: {self.score}", True, YELLOW)
        self.screen.blit(score_surf, (10, 8))

        # Status
        if self.paused:
            txt = self.big_font.render("PAUSED", True, WHITE)
            self.screen.blit(txt, (WIDTH//2 - txt.get_width()//2, HEIGHT//2 - 60))
            hint = self.font.render("Tekan P untuk lanjut", True, GRAY)
            self.screen.blit(hint, (WIDTH//2 - hint.get_width()//2, HEIGHT//2 - 20))

        if self.game_over:
            over = self.big_font.render("GAME OVER", True, WHITE)
            self.screen.blit(over, (WIDTH//2 - over.get_width()//2, HEIGHT//2 - 70))
            sub = self.font.render("Tekan R untuk restart atau ESC untuk keluar", True, GRAY)
            self.screen.blit(sub, (WIDTH//2 - sub.get_width()//2, HEIGHT//2 - 30))

        pygame.display.flip()

    def handle_input(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    return False
                if event.key in (pygame.K_UP, pygame.K_w):
                    self.snake.set_direction(UP)
                elif event.key in (pygame.K_DOWN, pygame.K_s):
                    self.snake.set_direction(DOWN)
                elif event.key in (pygame.K_LEFT, pygame.K_a):
                    self.snake.set_direction(LEFT)
                elif event.key in (pygame.K_RIGHT, pygame.K_d):
                    self.snake.set_direction(RIGHT)
                elif event.key == pygame.K_p:
                    self.paused = not self.paused
                elif event.key == pygame.K_r and self.game_over:
                    self.reset()
        return True

    def run(self):
        while True:
            if not self.handle_input():
                break
            self.update()
            self.draw()
            self.clock.tick(self.speed)

        pygame.quit()


if __name__ == "__main__":
    Game().run()
