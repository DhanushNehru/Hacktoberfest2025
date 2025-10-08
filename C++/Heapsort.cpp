#include <bits/stdc++.h>
using namespace std;

// Heapsort (max-heap) implementation
// Time: O(n log n), Space: O(1) (in-place)

void heapify(vector<int>& a, int n, int i) {
    int largest = i;
    int l = 2*i + 1;
    int r = 2*i + 2;
    if (l < n && a[l] > a[largest]) largest = l;
    if (r < n && a[r] > a[largest]) largest = r;
    if (largest != i) {
        swap(a[i], a[largest]);
        heapify(a, n, largest);
    }
}

void heapSort(vector<int>& a) {
    int n = (int)a.size();
    // Build heap (rearrange array)
    for (int i = n/2 - 1; i >= 0; --i) heapify(a, n, i);
    // One by one extract elements
    for (int i = n - 1; i > 0; --i) {
        swap(a[0], a[i]); // Move current root to end
        heapify(a, i, 0); // call max heapify on the reduced heap
    }
}

int main() {
    vector<int> a = {12, 11, 13, 5, 6, 7};
    cout << "Original: ";
    for (int x : a) cout << x << " ";
    cout << '\n';

    heapSort(a);

    cout << "Sorted:   ";
    for (int x : a) cout << x << " ";
    cout << '\n';

    return 0;
}
