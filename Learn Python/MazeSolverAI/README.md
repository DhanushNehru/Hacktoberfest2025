# Maze Solver AI - A* Pathfinding  

## Overview
Maze Solver AI is an interactive visualization tool that demonstrates the A* pathfinding algorithm in action. Built with Python and Pygame, it allows users to create custom mazes, set start and end points, and observe how the algorithm navigates the maze in real-time. This tool is ideal for educational purposes, helping users understand the mechanics of pathfinding algorithms.

The project helps visualize how the A* algorithm works in solving mazes, providing an easy-to-understand graphical representation.

## Features
**Interactive Grid**: Click to set start (orange) and end (purple) points. Drag to place barriers (black).
**Dynamic Maze Generation**: Random barriers appear over time, simulating a changing environment.
**Real-Time Visualization**: Watch the A* algorithm explore the maze step-by-step.
**User Controls:**
**Spacebar**: Start the pathfinding algorithm.
**Reset Button**: Clear the grid and start anew.
**Quit Button**: Exit the application.

## Pre-requisites
To run the Maze Solver AI, you need the following:
- Python 3.x
- Pygame library (for GUI and graphics)

1. To install Pygame:
  ```bash
  pip install pygame
  ```

## Usage
1. Run the program:
```bash
python maze_solver.py

## Interact with the Grid:
- **Set Start Point**: Click on a cell to designate the start point (**orange**).
- **Set End Point**: Click on another cell to set the end point (**purple**).
- **Place Barriers**: Barriers (**black**) are generated randomly to obstruct the path.
- **Start Algorithm**: Press the **Spacebar** to initiate the A* pathfinding algorithm.
- **Reset Grid**: Click the Reset button to clear the grid.
- **Quit Application**: Click the Quit button to exit.

## Color Legend
- Orange: Start point
- Purple: End point
- Black: Barriers (impassable)
- Yellow: Final path (shortest path)
- Light Blue: Open nodes (nodes currently being considered)
- Pastel Red: Closed nodes (nodes already evaluated)

## Instructions
1. Place the start and end points by clicking on the grid.
2. Use the mouse to drag and place barriers that will block the path.
3. Once you're ready, hit the **Spacebar** to run the algorithm and find the shortest path.
4. The grid can be **reset** at any time using the Reset button.
5. To exit the program, click the **Quit** button.

## Notes
1. The algorithm uses the Manhattan distance heuristic for pathfinding.
2. Random barriers may appear over time, adding complexity to the maze.
3. Ensure that both start and end points are set before initiating the algorithm.




