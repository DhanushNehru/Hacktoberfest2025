#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> bfs_from_zero(int V, const vector<vector<int>>& adj) {
    vector<int> order;
    if (V <= 0) return order;

    vector<char> vis(V, 0);
    queue<int> q;

    if (0 >= V) return order; // avoid starting node outside range
    q.push(0);
    vis[0] = 1;

    while (!q.empty()) {
        int u = q.front(); q.pop();
        order.push_back(u);
        for (int v : adj[u])
            if (v >= 0 && v < V && !vis[v]) { vis[v] = 1; q.push(v); }
    }
    return order;
}

int main() {
    // Example 1
    int V1 = 5;
    vector<vector<int>> adj1 = {{1,2},{},{3},{},{}};
    auto o1 = bfs_from_zero(V1, adj1);
    for (size_t i = 0; i < o1.size(); ++i) {
        cout << o1[i] << (i + 1 == o1.size() ? '\n' : ' ');
    }

    // Example 2
    int V2 = 3;
    vector<vector<int>> adj2 = {{1,2},{},{}};
    auto o2 = bfs_from_zero(V2, adj2);
    for (size_t i = 0; i < o2.size(); ++i) {
        cout << o2[i] << (i + 1 == o2.size() ? '\n' : ' ');
    }

    // Simple error checks (optional): demonstrate invalid input handling
    // Example: invalid V
    int V_invalid = -1;
    auto invalid = bfs_from_zero(V_invalid, adj1);
    if (invalid.empty()) {
        cerr << "Error: invalid vertex count or unreachable start\n";
    }

    return 0;
}