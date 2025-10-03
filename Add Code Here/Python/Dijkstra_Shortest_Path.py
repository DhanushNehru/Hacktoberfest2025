"""
Dijkstra's Shortest Path Algorithm Implementation
=================================================

Dijkstra's algorithm finds the shortest path from a source vertex to all other 
vertices in a weighted graph with non-negative edge weights.

Time Complexity: O((V + E) log V) with priority queue, O(V²) with array
Space Complexity: O(V)

Where:
- V is the number of vertices
- E is the number of edges

Author: GitHub Copilot
Date: October 2025
"""

import heapq
from collections import defaultdict
import sys


class Graph:
    """
    Graph class to represent a weighted directed graph using adjacency list.
    """
    
    def __init__(self, vertices):
        """
        Initialize graph with given number of vertices.
        
        Args:
            vertices: Number of vertices in the graph
        """
        self.V = vertices
        self.graph = defaultdict(list)
    
    def add_edge(self, u, v, weight):
        """
        Add a weighted edge to the graph.
        
        Args:
            u: Source vertex
            v: Destination vertex
            weight: Weight of the edge
        """
        self.graph[u].append((v, weight))
    
    def dijkstra(self, source):
        """
        Find shortest paths from source to all other vertices using Dijkstra's algorithm.
        
        Args:
            source: Starting vertex
        
        Returns:
            Dictionary containing shortest distances from source to all vertices
        """
        # Initialize distances with infinity
        distances = {i: float('infinity') for i in range(self.V)}
        distances[source] = 0
        
        # Priority queue to store (distance, vertex)
        pq = [(0, source)]
        
        # To keep track of visited vertices
        visited = set()
        
        # Store the shortest path tree
        parent = {i: None for i in range(self.V)}
        
        while pq:
            # Get vertex with minimum distance
            current_distance, current_vertex = heapq.heappop(pq)
            
            # Skip if already visited
            if current_vertex in visited:
                continue
            
            visited.add(current_vertex)
            
            # If current distance is greater than stored distance, skip
            if current_distance > distances[current_vertex]:
                continue
            
            # Check all neighbors
            for neighbor, weight in self.graph[current_vertex]:
                distance = current_distance + weight
                
                # If shorter path found
                if distance < distances[neighbor]:
                    distances[neighbor] = distance
                    parent[neighbor] = current_vertex
                    heapq.heappush(pq, (distance, neighbor))
        
        return distances, parent
    
    def get_shortest_path(self, parent, source, destination):
        """
        Reconstruct the shortest path from source to destination.
        
        Args:
            parent: Parent dictionary from dijkstra algorithm
            source: Starting vertex
            destination: Ending vertex
        
        Returns:
            List representing the shortest path
        """
        path = []
        current = destination
        
        # Reconstruct path from destination to source
        while current is not None:
            path.append(current)
            current = parent[current]
        
        # Reverse to get path from source to destination
        path.reverse()
        
        # Check if path exists
        if path[0] != source:
            return []
        
        return path
    
    def print_solution(self, distances, parent, source):
        """
        Print the shortest distances and paths from source to all vertices.
        
        Args:
            distances: Dictionary of shortest distances
            parent: Parent dictionary for path reconstruction
            source: Source vertex
        """
        print(f"\nShortest distances from vertex {source}:")
        print("-" * 60)
        print(f"{'Vertex':<10} {'Distance':<15} {'Path'}")
        print("-" * 60)
        
        for vertex in range(self.V):
            if distances[vertex] == float('infinity'):
                print(f"{vertex:<10} {'∞':<15} No path exists")
            else:
                path = self.get_shortest_path(parent, source, vertex)
                path_str = " -> ".join(map(str, path))
                print(f"{vertex:<10} {distances[vertex]:<15} {path_str}")


def example_1():
    """
    Example 1: Simple graph with 5 vertices
    """
    print("\n" + "=" * 60)
    print("EXAMPLE 1: Simple Graph")
    print("=" * 60)
    
    # Create a graph with 5 vertices
    g = Graph(5)
    
    # Add edges (u, v, weight)
    g.add_edge(0, 1, 4)
    g.add_edge(0, 2, 1)
    g.add_edge(2, 1, 2)
    g.add_edge(1, 3, 1)
    g.add_edge(2, 3, 5)
    g.add_edge(3, 4, 3)
    
    print("\nGraph edges:")
    print("0 -> 1 (weight: 4)")
    print("0 -> 2 (weight: 1)")
    print("2 -> 1 (weight: 2)")
    print("1 -> 3 (weight: 1)")
    print("2 -> 3 (weight: 5)")
    print("3 -> 4 (weight: 3)")
    
    source = 0
    distances, parent = g.dijkstra(source)
    g.print_solution(distances, parent, source)


def example_2():
    """
    Example 2: More complex graph
    """
    print("\n" + "=" * 60)
    print("EXAMPLE 2: Complex Graph")
    print("=" * 60)
    
    # Create a graph with 9 vertices
    g = Graph(9)
    
    # Add edges representing a road network
    edges = [
        (0, 1, 4), (0, 7, 8),
        (1, 2, 8), (1, 7, 11),
        (2, 3, 7), (2, 8, 2), (2, 5, 4),
        (3, 4, 9), (3, 5, 14),
        (4, 5, 10),
        (5, 6, 2),
        (6, 7, 1), (6, 8, 6),
        (7, 8, 7)
    ]
    
    print("\nGraph edges:")
    for u, v, w in edges:
        g.add_edge(u, v, w)
        print(f"{u} -> {v} (weight: {w})")
    
    source = 0
    distances, parent = g.dijkstra(source)
    g.print_solution(distances, parent, source)


def example_3():
    """
    Example 3: Disconnected graph
    """
    print("\n" + "=" * 60)
    print("EXAMPLE 3: Disconnected Graph")
    print("=" * 60)
    
    # Create a graph with 6 vertices
    g = Graph(6)
    
    # Add edges (vertices 4 and 5 are disconnected)
    g.add_edge(0, 1, 2)
    g.add_edge(0, 2, 4)
    g.add_edge(1, 2, 1)
    g.add_edge(1, 3, 7)
    g.add_edge(2, 3, 3)
    
    print("\nGraph edges:")
    print("0 -> 1 (weight: 2)")
    print("0 -> 2 (weight: 4)")
    print("1 -> 2 (weight: 1)")
    print("1 -> 3 (weight: 7)")
    print("2 -> 3 (weight: 3)")
    print("\nNote: Vertices 4 and 5 are not connected to the graph")
    
    source = 0
    distances, parent = g.dijkstra(source)
    g.print_solution(distances, parent, source)


def main():
    """
    Main function to run all examples.
    """
    print("\n")
    print("*" * 60)
    print("DIJKSTRA'S SHORTEST PATH ALGORITHM DEMONSTRATION")
    print("*" * 60)
    
    example_1()
    example_2()
    example_3()
    
    print("\n" + "*" * 60)
    print("All examples completed successfully!")
    print("*" * 60)
    print("\nKey Points:")
    print("- Dijkstra's algorithm works only with non-negative weights")
    print("- It finds the shortest path from source to all other vertices")
    print("- Time complexity: O((V + E) log V) with min-heap")
    print("- Space complexity: O(V)")
    print("*" * 60 + "\n")


if __name__ == "__main__":
    main()
