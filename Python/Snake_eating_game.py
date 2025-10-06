import tkinter as tk
import random

class SnakeGame:
    def __init__(self, root):
        self.root = root
        self.root.title("Snake Game")
        self.root.resizable(False, False)

        self.canvas_width = 600
        self.canvas_height = 600
        self.canvas = tk.Canvas(root, width=self.canvas_width, height=self.canvas_height, bg="black")
        self.canvas.pack(expand=True)

        self.snake = [(20, 20), (20, 40), (20, 60)]
        self.snake_direction = "Down"
        self.food = None
        self.create_food()

        self.running = True
        self.score = 0

        self.root.bind("<Up>", self.change_direction)
        self.root.bind("<Down>", self.change_direction)
        self.root.bind("<Left>", self.change_direction)
        self.root.bind("<Right>", self.change_direction)

        self.update_snake()

    def create_food(self):
        x = random.randint(0, (self.canvas_width // 20) - 1) * 20
        y = random.randint(0, (self.canvas_height // 20) - 1) * 20
        self.food = (x, y)
        self.canvas.create_rectangle(x, y, x + 20, y + 20, fill="red", tags="food")

    def change_direction(self, event):
        if event.keysym == "Up" and self.snake_direction != "Down":
            self.snake_direction = "Up"
        elif event.keysym == "Down" and self.snake_direction != "Up":
            self.snake_direction = "Down"
        elif event.keysym == "Left" and self.snake_direction != "Right":
            self.snake_direction = "Left"
        elif event.keysym == "Right" and self.snake_direction != "Left":
            self.snake_direction = "Right"

    def update_snake(self):
        if not self.running:
            return

        x, y = self.snake[-1]

        if self.snake_direction == "Up":
            y -= 20
        elif self.snake_direction == "Down":
            y += 20
        elif self.snake_direction == "Left":
            x -= 20
        elif self.snake_direction == "Right":
            x += 20

        new_head = (x, y)

        if new_head in self.snake or x < 0 or x >= self.canvas_width or y < 0 or y >= self.canvas_height:
            self.running = False
            self.canvas.create_text(self.canvas_width // 2, self.canvas_height // 2 - 20, text="Game Over", fill="white", font=("Helvetica", 24))
            self.canvas.create_text(self.canvas_width // 2, self.canvas_height // 2 + 20, text=f"Score: {self.score}", fill="white", font=("Helvetica", 24))
            self.canvas.create_text(self.canvas_width // 2, self.canvas_height // 2 + 60, text="Press R to Play Again", fill="white", font=("Helvetica", 18))
            self.root.bind("<r>", self.reset_game)
            return

        self.snake.append(new_head)

        if new_head == self.food:
            self.score += 1
            self.canvas.delete("food")
            self.create_food()
        else:
            self.snake.pop(0)

        self.canvas.delete("snake")
        for segment in self.snake:
            self.canvas.create_rectangle(segment[0], segment[1], segment[0] + 20, segment[1] + 20, fill="green", tags="snake")

        self.root.after(100, self.update_snake)

    def reset_game(self, event):
        self.canvas.delete("all")
        self.snake = [(20, 20), (20, 40), (20, 60)]
        self.snake_direction = "Down"
        self.food = None
        self.create_food()
        self.running = True
        self.score = 0
        self.root.bind("<Up>", self.change_direction)
        self.root.bind("<Down>", self.change_direction)
        self.root.bind("<Left>", self.change_direction)
        self.root.bind("<Right>", self.change_direction)
        self.update_snake()

root = tk.Tk()
root.geometry("+{}+{}".format(int(root.winfo_screenwidth() / 2 - 300), int(root.winfo_screenheight() / 2 - 300)))
game = SnakeGame(root)
root.mainloop()
