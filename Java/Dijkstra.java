import java.util.*;

public class Dijkstra {
    static class Edge {
        int to;
        int w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    public static long[] dijkstra(int n, List<Edge>[] g, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 4);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] { 0, src });
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d != dist[u]) continue;
            for (Edge e : g[u]) {
                int v = e.to;
                long nd = d + e.w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[] { nd, v });
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 6;
        List<Edge>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        g[0].add(new Edge(1, 7));
        g[0].add(new Edge(2, 9));
        g[0].add(new Edge(5, 14));
        g[1].add(new Edge(2, 10));
        g[1].add(new Edge(3, 15));
        g[2].add(new Edge(3, 11));
        g[2].add(new Edge(5, 2));
        g[3].add(new Edge(4, 6));
        g[4].add(new Edge(5, 9));
        long[] dist = dijkstra(n, g, 0);
        System.out.println(Arrays.toString(dist));
    }
}
