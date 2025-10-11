/*
Disjoint Set Union (DSU) or Union-Find data structure
Used to keep track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
Supports two main operations:
1. Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
2. Union: Join two subsets into a single subset
Time Complexity: Almost constant time, O(α(n)), where α is the inverse Ackermann
*/
#include <iostream>   
#include <vector>
using namespace std;
struct DSU {
  vector<int> e;
  void init (int n) { e = vector<int>(n, -  1); }
  int get (int x) { 
    return e[x] < 0 ? x : e[x] = get(e[x]); }//gets the father node
  bool sameSet (int x, int y) { 
    return get(x) == get(y); }//check if same father node
  int size (int x) { return -e[get(x)]; }//opposite value stored in father node
  bool unite (int x, int y) {
    x = get(x), y = get(y);//father node of the 2 nodes
    if (x == y) return false;//if same father node no need to combine
    if (e[x] > e[y]) swap(x, y);//y has larger size graph, swap so x is the larger size one
    e[x] += e[y];//add y to x
    e[y] = x;//father node of y is now x
    return true;
  }
};