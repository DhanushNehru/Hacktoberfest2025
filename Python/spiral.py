import turtle
import random

# Setup the screen
screen = turtle.Screen()
screen.bgcolor("black")

# Create a turtle
spiral = turtle.Turtle()
spiral.speed(0)
colors = ["red", "orange", "yellow", "green", "blue", "purple"]

# Draw the spiral
for i in range(200):
    spiral.color(random.choice(colors))
    spiral.width(i / 20 + 1)
    spiral.forward(i)
    spiral.left(59)

# Hide the turtle
spiral.hideturtle()

# Keep the window open
turtle.done()
