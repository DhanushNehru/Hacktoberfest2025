#include <bits/stdc++.h>
using namespace std;

vector<int> mergeSortedArrays(vector<int>& arr1, vector<int>& arr2) {
    int n1 = arr1.size();
    int n2 = arr2.size();
    vector<int> merged;
    merged.reserve(n1 + n2);

    int i = 0, j = 0;
    while (i < n1 && j < n2) {
        if (arr1[i] <= arr2[j])
            merged.push_back(arr1[i++]);
        else
            merged.push_back(arr2[j++]);
    }

    while (i < n1) merged.push_back(arr1[i++]);
    while (j < n2) merged.push_back(arr2[j++]);

    return merged;
}

int main() {
    int n1, n2;
    cout << "Enter size of first array: ";
    cin >> n1;
    vector<int> arr1(n1);
    cout << "Enter elements of first sorted array: ";
    for (int i = 0; i < n1; i++) cin >> arr1[i];

    cout << "Enter size of second array: ";
    cin >> n2;
    vector<int> arr2(n2);
    cout << "Enter elements of second sorted array: ";
    for (int i = 0; i < n2; i++) cin >> arr2[i];

    vector<int> result = mergeSortedArrays(arr1, arr2);

    cout << "Merged sorted array: ";
    for (int num : result) cout << num << " ";
    cout << endl;

    return 0;
}
