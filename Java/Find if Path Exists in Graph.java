import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]); // undirected
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, source, destination, visited);
    }

    private boolean dfs(List<List<Integer>> graph, int node, int destination, boolean[] visited) {
        if (node == destination) return true;
        if (visited[node]) return false;

        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (dfs(graph, neighbor, destination, visited)) {
                return true;
            }
        }
        return false;
    }
}
