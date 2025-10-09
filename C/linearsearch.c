#include <stdio.h>

int linearSearch(int arr[], int n, int target) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            return i;  // Element found, return index
        }
    }
    return -1;  // Element not found
}

int main() {
    int arr[] = {10, 25, 30, 45, 50, 60};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 45;

    int result = linearSearch(arr, n, target);

    if (result != -1)
        printf("✅ Element %d found at index %d\n", target, result);
    else
        printf("❌ Element %d not found in the array\n", target);

    return 0;
}
