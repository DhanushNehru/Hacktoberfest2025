import turtle


def draw_triangle(points, color, my_turtle):
    """Draws a single filled triangle."""
    my_turtle.fillcolor(color)
    my_turtle.up()
    my_turtle.goto(points[0])
    my_turtle.down()
    my_turtle.begin_fill()
    my_turtle.goto(points[1])
    my_turtle.goto(points[2])
    my_turtle.goto(points[0])
    my_turtle.end_fill()


def get_mid(p1, p2):
    """Calculates the midpoint between two points."""
    return ((p1[0] + p2[0]) / 2, (p1[1] + p2[1]) / 2)


def sierpinski(points, level, my_turtle):
    """Recursively draws the Sierpinski triangle."""
    if level == 0:
        draw_triangle(points, "blue", my_turtle)
    else:
        p1, p2, p3 = points

        # Midpoints
        mid12 = get_mid(p1, p2)
        mid23 = get_mid(p2, p3)
        mid31 = get_mid(p3, p1)

        # Recursive calls
        sierpinski([p1, mid12, mid31], level - 1, my_turtle)
        sierpinski([mid12, p2, mid23], level - 1, my_turtle)
        sierpinski([mid31, mid23, p3], level - 1, my_turtle)


# --- Main setup ---
if __name__ == "__main__":
    screen = turtle.Screen()
    screen.title("Sierpinski Triangle")
    my_turtle = turtle.Turtle()
    my_turtle.speed(0)

    # Write Heading
    my_turtle.penup()
    my_turtle.goto(0, 270)
    my_turtle.color("darkblue")
    my_turtle.write("Sierpinski Triangle", align="center", font=("Arial", 24, "bold"))

    # Write Subheading (mefaizankhan)
    my_turtle.goto(0, 240)
    my_turtle.color("gray")
    my_turtle.write("by @mefaizankhan", align="center", font=("Arial", 16, "italic"))

    my_turtle.goto(0, 0)
    my_turtle.pendown()

    # Define triangle points
    initial_points = [(-200, -150), (0, 250), (200, -150)]
    recursion_level = 5

    # Draw the fractal
    sierpinski(initial_points, recursion_level, my_turtle)

    my_turtle.hideturtle()
    screen.mainloop()
