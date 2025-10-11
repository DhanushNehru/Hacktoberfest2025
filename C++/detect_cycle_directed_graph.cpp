/*
    Problem: Detect Cycle in a Directed Graph
    Difficulty: Medium
    Topic: Graphs, DFS, Cycle Detection

    --------------------------------------------
    Problem Statement:
    Given a directed graph with n vertices and m edges,
    determine whether it contains a cycle.

    --------------------------------------------
    Input:
    n = 4, m = 4
    Edges:
    0 -> 1
    1 -> 2
    2 -> 0
    2 -> 3

    Output:
    Cycle detected!

    --------------------------------------------
    Approach:
    - Use DFS with recursion stack tracking.
    - If a node is revisited while still in the recursion stack → cycle exists.

    Time Complexity: O(V + E)
    Space Complexity: O(V)
*/

#include <bits/stdc++.h>
using namespace std;

bool dfsCycle(int node, vector<vector<int>>& adj, vector<int>& vis, vector<int>& recStack) {
    vis[node] = 1;
    recStack[node] = 1;

    for (int nei : adj[node]) {
        if (!vis[nei] && dfsCycle(nei, adj, vis, recStack))
            return true;
        else if (recStack[nei])
            return true;
    }

    recStack[node] = 0;
    return false;
}

bool hasCycle(int n, vector<vector<int>>& adj) {
    vector<int> vis(n, 0), recStack(n, 0);
    for (int i = 0; i < n; i++)
        if (!vis[i] && dfsCycle(i, adj, vis, recStack))
            return true;
    return false;
}

int main() {
    int n, m;
    cout << "Enter number of vertices and edges: ";
    cin >> n >> m;

    vector<vector<int>> adj(n);
    cout << "Enter edges (u v): \n";
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
    }

    if (hasCycle(n, adj))
        cout << "Cycle detected!" << endl;
    else
        cout << "No cycle found." << endl;

    return 0;
}
