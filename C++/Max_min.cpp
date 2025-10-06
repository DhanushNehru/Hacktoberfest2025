//The Max-Min algorithm uses a divide-and-conquer approach to find the maximum and minimum elements in an array. 
//The code recursively splits the array into smaller segments, compares values within each, and combines the results
//to determine the overall maximum and minimum. The main function reads the array and calls the recursive function `maxmin` 
//to compute and print these values.

#include <iostream>
using namespace std;

void maxmin(int arr[], int i, int j, int &max1, int &min1) {
    // Base case: If there's only one element
    if (i == j) {
        max1 = arr[i];
        min1 = arr[i];
    }
    // If there are two elements
    else if (i + 1 == j) {
        if (arr[i] > arr[j]) {
            max1 = arr[i];
            min1 = arr[j];
        } else {
            max1 = arr[j];
            min1 = arr[i];
        }
    }
    // More than two elements, divide the array
    else {
        int mid = (i + j) / 2;
        int max2, min2;
        maxmin(arr, i, mid, max1, min1);
        maxmin(arr, mid + 1, j, max2, min2);

        // Combine results from two halves
        if (max1 < max2) {
            max1 = max2;
        }
        if (min1 > min2) {
            min1 = min2;
        }
    }
}

int main() {
    int n;
    cout << "Enter size of array: ";
    cin >> n;
    int arr[n];
    cout << "Enter elements: ";
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    int max1 = arr[0];
    int min1 = arr[0];

    maxmin(arr, 0, n - 1, max1, min1);
    cout << "MAX: " << max1 << "\n";
    cout << "MIN: " << min1 << "\n";

    return 0;
}
