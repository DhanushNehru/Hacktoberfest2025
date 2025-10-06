#include <iostream>
using namespace std;

void reverse(int arr[], int start, int end);
void rotateArray(int arr[], int n, int k);

void rotateArray(int arr[], int n, int k) {
    // Normalize k in case it's larger than n
    k = k % n;
    if (k == 0) return; // No rotation needed

    // Reverse the whole array
    reverse(arr, 0, n);
    // Reverse the first k elements
    reverse(arr, 0, k);
    // Reverse the remaining n-k elements
    reverse(arr, k, n);
}

void reverse(int arr[], int start, int end) {
    while (start < end) {
        swap(arr[start], arr[end - 1]);
        start++;
        end--;
    }
}

int main() {
    int arr[] = {1, 2, 3, 4, 5, 6, 7};
    int n = sizeof(arr) / sizeof(arr[0]);
    int k = 3;

    rotateArray(arr, n, k);

    cout << "Rotated array: ";
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    return 0;
}
