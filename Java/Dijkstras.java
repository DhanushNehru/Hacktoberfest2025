import java.util.*;

class Dijkstras {
    private int vertices;
    private List<List<Node>> adjList;
    private boolean directed;

    // Node class for adjacency list
    static class Node {
        int vertex, weight;
        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    Dijkstras(int v, boolean directed) {
        this.vertices = v;
        this.directed = directed;
        adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Node(dest, weight));
        if (!directed) { // if undirected, add reverse edge
            adjList.get(dest).add(new Node(src, weight));
        }
    }

    void dijkstra(int start) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : adjList.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        printSolution(dist, start);
    }

    void printSolution(int[] dist, int start) {
        System.out.println("Shortest distances from vertex " + start + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To " + i + " -> " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input graph details
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.print("Is the graph directed? (yes=1 / no=0): ");
        boolean directed = sc.nextInt() == 1;

        Dijkstras g = new Dijkstras(v, directed);

        System.out.println("Enter edges in format: src dest weight");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(src, dest, w);
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        g.dijkstra(src);
        sc.close();
    }
}

