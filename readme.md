# Dijkstra's Algorithm in Python

This Python script implements **Dijkstra's Algorithm** to find the shortest paths from a source vertex in a weighted graph with non-negative edge weights.

## How It Works

1. Set the distance to the source vertex as 0, all others as infinity.
2. Use a min-heap to select the vertex with the smallest known distance.
3. For each neighbor, update the distance if a shorter path is found.
4. Repeat until all vertices are processed.

## Getting Started

### Prerequisites

Ensure Python is installed. [Download here](https://www.python.org/downloads/).

### Running the Script

1. Clone the repository.
2. Run the script using:

   ```bash
   python dijkstra.py

INPUT FORMAT

The input consists of:

* Number of vertices: Enter the total number of vertices in the graph.
* Graph representation:
      For each vertex:
    * Input the vertex name.
    * Enter the number of edges connected to that vertex.
    * For each edge, input the neighboring vertex and the edge weight, separated by a space.
* Source vertex: Enter the name of the source vertex from which the shortest paths will be calculated.
<pre>
example: 4
A                              
2
B 1
C 4
B
3
A 1
C 2
D 5
C
3
A 4
B 2
D 1
D
2
B 5
C 1
A
</pre>
<pre>
4: Number of vertices in the graph.
A, B, C, D: The vertices of the graph.
B 1, C 4: Vertex A has edges to B with weight 1, and to C with weight 4.
A: The source vertex for shortest path calculations.
</pre>
<pre>
OUTPUT FORMAT
Shortest distances from A: {'A': 0, 'B': 1, 'C': 3, 'D': 4}
</pre>
