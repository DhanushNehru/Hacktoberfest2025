#include <iostream>
#include <vector>
using namespace std;

// Disjoint Set Union with Union by Rank
class DSU_Rank {
private:
    vector<int> parent;
    vector<int> rank;

public:
    DSU_Rank(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union by rank
    void unionSets(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Check if two elements are in the same set
    bool connected(int x, int y) {
        return find(x) == find(y);
    }
};

// Disjoint Set Union with Union by Size
class DSU_Size {
private:
    vector<int> parent;
    vector<int> size;

public:
    DSU_Size(int n) {
        parent.resize(n);
        size.resize(n, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union by size
    void unionSets(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }

    // Check if two elements are in the same set
    bool connected(int x, int y) {
        return find(x) == find(y);
    }

    // Get size of the set containing element x
    int getSize(int x) {
        return size[find(x)];
    }
};

// Demo function
int main() {
    cout << "Disjoint Set Union Demo\n";
    cout << "======================\n\n";

    // Demo with Union by Rank
    cout << "Union by Rank:\n";
    DSU_Rank dsuRank(6);
    
    dsuRank.unionSets(0, 1);
    dsuRank.unionSets(2, 3);
    dsuRank.unionSets(4, 5);
    dsuRank.unionSets(0, 2);
    
    cout << "After unions (0,1), (2,3), (4,5), (0,2):\n";
    cout << "Are 1 and 3 connected? " << (dsuRank.connected(1, 3) ? "Yes" : "No") << "\n";
    cout << "Are 1 and 5 connected? " << (dsuRank.connected(1, 5) ? "Yes" : "No") << "\n";

    // Demo with Union by Size
    cout << "\nUnion by Size:\n";
    DSU_Size dsuSize(6);
    
    dsuSize.unionSets(0, 1);
    dsuSize.unionSets(2, 3);
    dsuSize.unionSets(4, 5);
    dsuSize.unionSets(0, 2);
    
    cout << "After unions (0,1), (2,3), (4,5), (0,2):\n";
    cout << "Are 1 and 3 connected? " << (dsuSize.connected(1, 3) ? "Yes" : "No") << "\n";
    cout << "Are 1 and 5 connected? " << (dsuSize.connected(1, 5) ? "Yes" : "No") << "\n";
    cout << "Size of set containing element 1: " << dsuSize.getSize(1) << "\n";
    cout << "Size of set containing element 5: " << dsuSize.getSize(5) << "\n";

    return 0;
}