#include <iostream>
using namespace std;

int binarySearch(int arr[], int n, int key) {
    int low = 0;
    int high = n - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2; // Overflow avoid karne ke liye

        if (arr[mid] == key)
            return mid; // Element mil gaya
        else if (arr[mid] < key)
            low = mid + 1; // Right half me search
        else
            high = mid - 1; // Left half me search
    }

    return -1; // Element nahi mila
}

int main() {
    int arr[] = {1, 3, 5, 7, 9, 11};
    int n = sizeof(arr) / sizeof(arr[0]);
    int key = 7;

    int result = binarySearch(arr, n, key);
    if (result != -1)
        cout << "Element found at index " << result << endl;
    else
        cout << "Element not found" << endl;

    return 0;
}
