import heapq

def dijkstra(graph, source):
    distances = {vertex: float('inf') for vertex in graph}
    distances[source] = 0
    priority_queue = [(0, source)]

    while priority_queue:
        current_distance, current_vertex = heapq.heappop(priority_queue)

        if current_distance > distances[current_vertex]:
            continue

        for neighbor, weight in graph[current_vertex].items():
            distance = current_distance + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances

if __name__ == "__main__":
    graph = {}
    vertices = int(input("Enter number of vertices: "))
    
    for _ in range(vertices):
        vertex = input("Enter vertex: ")
        edges = int(input(f"Enter number of edges for {vertex}: "))
        graph[vertex] = {}
        for _ in range(edges):
            neighbor, weight = input("Enter neighbor and weight separated by space: ").split()
            graph[vertex][neighbor] = int(weight)
    
    source_vertex = input("Enter the source vertex: ")
    shortest_distances = dijkstra(graph, source_vertex)
    print(f"Shortest distances from {source_vertex}: {shortest_distances}")
