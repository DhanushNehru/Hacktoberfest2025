import curses
import random
import time
from collections import deque
from enum import Enum

class Direction(Enum):
    UP = (-1, 0)
    DOWN = (1, 0)
    LEFT = (0, -1)
    RIGHT = (0, 1)

class SnakeGame:
    def __init__(self, width=40, height=20):
        self.width = width
        self.height = height
        self.reset_game()
        self.ai_mode = False
        self.game_speed = 0.1
        
    def reset_game(self):
        self.snake = deque([(self.height // 2, self.width // 2)])
        self.direction = Direction.RIGHT
        self.food = self.spawn_food()
        self.score = 0
        self.game_over = False
        
    def spawn_food(self):
        while True:
            food = (random.randint(1, self.height - 2), 
                   random.randint(1, self.width - 2))
            if food not in self.snake:
                return food
    
    def move_snake(self):
        head = self.snake[0]
        dy, dx = self.direction.value
        new_head = (head[0] + dy, head[1] + dx)
        
        # Check collisions
        if (new_head[0] <= 0 or new_head[0] >= self.height - 1 or
            new_head[1] <= 0 or new_head[1] >= self.width - 1 or
            new_head in self.snake):
            self.game_over = True
            return
        
        self.snake.appendleft(new_head)
        
        # Check if food eaten
        if new_head == self.food:
            self.score += 10
            self.food = self.spawn_food()
        else:
            self.snake.pop()
    
    def change_direction(self, new_direction):
        # Prevent 180-degree turns
        opposite = {
            Direction.UP: Direction.DOWN,
            Direction.DOWN: Direction.UP,
            Direction.LEFT: Direction.RIGHT,
            Direction.RIGHT: Direction.LEFT
        }
        if new_direction != opposite[self.direction]:
            self.direction = new_direction
    
    def bfs_to_food(self):
        """AI pathfinding using BFS"""
        head = self.snake[0]
        target = self.food
        
        queue = deque([(head, [])])
        visited = {head}
        
        while queue:
            (y, x), path = queue.popleft()
            
            if (y, x) == target:
                if path:
                    return path[0]
                return self.direction
            
            for direction in Direction:
                dy, dx = direction.value
                new_pos = (y + dy, x + dx)
                
                if (new_pos not in visited and
                    0 < new_pos[0] < self.height - 1 and
                    0 < new_pos[1] < self.width - 1 and
                    new_pos not in list(self.snake)[:-1]):
                    
                    visited.add(new_pos)
                    queue.append((new_pos, path + [direction]))
        
        # If no path found, try to survive
        for direction in Direction:
            dy, dx = direction.value
            new_pos = (head[0] + dy, head[1] + dx)
            opposite = {
                Direction.UP: Direction.DOWN,
                Direction.DOWN: Direction.UP,
                Direction.LEFT: Direction.RIGHT,
                Direction.RIGHT: Direction.LEFT
            }
            
            if (0 < new_pos[0] < self.height - 1 and
                0 < new_pos[1] < self.width - 1 and
                new_pos not in self.snake and
                direction != opposite[self.direction]):
                return direction
        
        return self.direction

def draw_game(stdscr, game):
    stdscr.clear()
    
    # Draw border
    for i in range(game.height):
        for j in range(game.width):
            if i == 0 or i == game.height - 1 or j == 0 or j == game.width - 1:
                stdscr.addstr(i, j, '█')
    
    # Draw snake
    for i, (y, x) in enumerate(game.snake):
        if i == 0:
            stdscr.addstr(y, x, '●', curses.color_pair(1))  # Head
        else:
            stdscr.addstr(y, x, '○', curses.color_pair(2))  # Body
    
    # Draw food
    fy, fx = game.food
    stdscr.addstr(fy, fx, '★', curses.color_pair(3))
    
    # Draw score and info
    info_y = game.height + 1
    mode_text = "AI MODE" if game.ai_mode else "MANUAL MODE"
    stdscr.addstr(info_y, 0, f"Score: {game.score} | {mode_text} | Length: {len(game.snake)}")
    stdscr.addstr(info_y + 1, 0, "Controls: ↑↓←→ to move | 'a' for AI | 'r' to restart | 'q' to quit")
    
    if game.game_over:
        game_over_text = "GAME OVER! Press 'r' to restart"
        stdscr.addstr(game.height // 2, game.width // 2 - len(game_over_text) // 2, 
                     game_over_text, curses.color_pair(4))
    
    stdscr.refresh()

def main(stdscr):
    # Setup colors
    curses.curs_set(0)  # Hide cursor
    curses.init_pair(1, curses.COLOR_GREEN, curses.COLOR_BLACK)   # Snake head
    curses.init_pair(2, curses.COLOR_CYAN, curses.COLOR_BLACK)    # Snake body
    curses.init_pair(3, curses.COLOR_YELLOW, curses.COLOR_BLACK)  # Food
    curses.init_pair(4, curses.COLOR_RED, curses.COLOR_BLACK)     # Game over
    
    stdscr.nodelay(True)  # Non-blocking input
    stdscr.timeout(100)   # Refresh rate
    
    game = SnakeGame()
    
    print("╔═══════════════════════════════════════════╗")
    print("║       Snake Game with AI Started!        ║")
    print("╚═══════════════════════════════════════════╝")
    
    while True:
        try:
            key = stdscr.getch()
            
            # Handle input
            if key == ord('q'):
                break
            elif key == ord('r'):
                game.reset_game()
            elif key == ord('a'):
                game.ai_mode = not game.ai_mode
            elif not game.ai_mode and not game.game_over:
                if key == curses.KEY_UP:
                    game.change_direction(Direction.UP)
                elif key == curses.KEY_DOWN:
                    game.change_direction(Direction.DOWN)
                elif key == curses.KEY_LEFT:
                    game.change_direction(Direction.LEFT)
                elif key == curses.KEY_RIGHT:
                    game.change_direction(Direction.RIGHT)
            
            # AI makes decision
            if game.ai_mode and not game.game_over:
                ai_direction = game.bfs_to_food()
                game.change_direction(ai_direction)
            
            # Update game
            if not game.game_over:
                game.move_snake()
            
            # Draw
            draw_game(stdscr, game)
            
            # Control speed
            time.sleep(game.game_speed)
            
        except KeyboardInterrupt:
            break
        except Exception as e:
            # Handle terminal resize or other errors gracefully
            pass

if __name__ == "__main__":
    try:
        curses.wrapper(main)
    except KeyboardInterrupt:
        print("\nGame closed. Thanks for playing!")
    print(f"\n🎮 Final Score: Thanks for playing Snake! 🐍")