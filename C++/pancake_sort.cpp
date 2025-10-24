#include <iostream>
using namespace std;

/*
Pancake Sort Algorithm
Time Complexity: O(n^2)
Space Complexity: O(1)
Description: Repeatedly flips the largest unsorted element to the top and then to its correct position,
similar to flipping pancakes in a stack.
*/

// Function to flip array[0..i]
void flip(int arr[], int i) {
    int start = 0;
    while(start < i) {
        int temp = arr[start];
        arr[start] = arr[i];
        arr[i] = temp;
        start++;
        i--;
    }
}

// Function to find index of maximum element in arr[0..n-1]
int findMax(int arr[], int n) {
    int maxIdx = 0;
    for(int i = 1; i < n; i++) {
        if(arr[i] > arr[maxIdx])
            maxIdx = i;
    }
    return maxIdx;
}

// Pancake Sort function
void pancakeSort(int arr[], int n) {
    for(int curr_size = n; curr_size > 1; --curr_size) {
        int maxIdx = findMax(arr, curr_size);

        // Move maximum number to end of current array if it's not already at the end
        if(maxIdx != curr_size-1) {
            flip(arr, maxIdx);      // Flip max to front
            flip(arr, curr_size-1); // Flip max to correct position
        }
    }
}

int main() {
    int n;
    cout << "Enter number of elements: ";
    cin >> n;

    int arr[n];
    cout << "Enter elements: ";
    for(int i = 0; i < n; i++) cin >> arr[i];

    pancakeSort(arr, n);

    cout << "Sorted array: ";
    for(int i = 0; i < n; i++) cout << arr[i] << " ";
    cout << endl;

    return 0;
}
