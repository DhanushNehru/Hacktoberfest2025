#include <iostream>
#include <queue>
#include <vector>
#include <stack>

using namespace std;

void dfs(vector<vector<int>> &adjList, vector<bool> visited, int startNode)
{
    stack<int> st;
    visited[startNode] = true;
    st.push(startNode);

    while (!st.empty())
    {
        int current = st.top();
        st.pop();
        visited[current] = true;
        cout << current << " ";
        for (int nei : adjList[current])
        {
            if (!visited[nei])
            {
                visited[nei] = true;
                st.push(nei);
            }
        }
    }
}

void bfs(vector<vector<int>> &adjList, int startNode,
         vector<bool> &visited)
{
    queue<int> q;

    visited[startNode] = true;
    q.push(startNode);

    while (!q.empty())
    {
        int currentNode = q.front();
        q.pop();
        cout << currentNode << " ";

        for (int nei : adjList[currentNode])
        {
            if (!visited[nei])
            {
                visited[nei] = true;
                q.push(nei);
            }
        }
    }
}

void addEdge(vector<vector<int>> &adjList, int u, int v)
{
    adjList[u].push_back(v);
}

int main()
{
    int n;
    cout << "Enter the number of vertices in the graph : ";
    cin >> n;

    vector<vector<int>> adjList(n);

    int choice = 1;
    while (choice)
    {
        cout << "Enter 1 to enter an edge and 0 to end : ";
        cin >> choice;
        if (choice)
        {
            int u, v;
            cout << "Enter 1st vertex followed by 2nd vertex : " << endl;
            cin >> u >> v;
            addEdge(adjList, u, v);
        }
        else
        {
            break;
        }
    }

    vector<bool> visited(n, false);

    cout << "Breadth First Traversal starting from vertex "
            "0: "
         << endl;
    bfs(adjList, 0, visited);

    fill(visited.begin(), visited.end(), false);

    cout << "\nDepth First Traversal starting from vertex "
            "0: "
         << endl;
    dfs(adjList, visited, 0);

    return 0;
}
