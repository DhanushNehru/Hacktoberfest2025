#include <bits/stdc++.h>
using namespace std;

int main()
{
    int N, E, a, b;
    cout << "Enter the number of vertices" << endl;
    cin >> N;
    cout << "Enter the number of edges" << endl;
    cin >> E;
    vector<vector<int>> Adjlist(N);
    for (int i = 0; i < E; i++)
    {
        cout << "Enter thre ends point of the edges" << i << endl;
        cin >> a >> b;
        Adjlist[a].push_back(b);
        Adjlist[b].push_back(a);
    }

    for (int i = 0; i < N; i++)
    {
        cout << i << ":";
        for (int j = 0; j < Adjlist[i].size(); j++)
        {
            cout << Adjlist[i][j] << ",";
        }
        cout << endl;
    }

    vector<int> status(N, 0);
    queue<int> q;
    q.push(0);
    status[0] = 1;
    while (!q.empty())
    {
        int x = q.front();
        q.pop();
        cout << x << " ";
        for (int i = 0; i < Adjlist[x].size(); i++)
        {
            int y = Adjlist[x][i];
            if (status[y] == 0)
            {
                status[y] = 1;
                q.push(y);
            }
        }
    }

    
}