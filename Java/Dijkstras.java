import java.util.*;

class Dijkstras {
    private int vertices;
    private int[][] graph;

    Dijkstras(int v) {
        vertices = v;
        graph = new int[v][v];
    }

    void addEdge(int src, int dest, int weight) {
        graph[src][dest] = weight;
        graph[dest][src] = weight; // for undirected
    }

    void dijkstra(int start) {
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Dijkstras g = new Dijkstras(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 4, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 4, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 0, 7);
        g.addEdge(3, 2, 6);
        g.addEdge(4, 1, 3);
        g.addEdge(4, 2, 9);
        g.addEdge(4, 3, 2);

        g.dijkstra(0);
    }
}

