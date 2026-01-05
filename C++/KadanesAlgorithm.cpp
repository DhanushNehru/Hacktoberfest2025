// Program to find the largest sum contiguous subarray (Kadane's Algorithm)
#include<iostream>
using namespace std;

int main()
{
    // Declare array and number of elements
    int arr[100], n;

    // Input number of elements
    cout << "Enter no of elements: ";
    cin >> n;

    // Input array elements
    cout << "\nEnter " << n << " elements: ";
    for(int i = 0; i < n; i++)
        cin >> arr[i];

    // Display entered elements
    cout << "\nEntered elements:\n";
    for(int i = 0; i < n; i++)
        cout << arr[i] << "\t";

    // -------------------------
    // Initialize variables for Kadane's Algorithm
    // -------------------------
    int maxsum = arr[0];    // To store maximum sum found so far
    int currsum = arr[0];   // To store current sum of subarray
    int tempstart, start, end; // Optional: to track subarray indices (not used here)

    // Loop through array starting from second element
    for(int i = 1; i < n; i++)
    {
        // Update current sum: either extend the current subarray or start new at arr[i]
        if(currsum + arr[i] >= arr[i])
            currsum = currsum + arr[i];
        else
            currsum = arr[i];

        // Update maximum sum if current sum is greater
        if(currsum > maxsum)
            maxsum = currsum;
    }

    // Display the maximum sum of contiguous subarray
    cout << "\nMaximum sum = " << maxsum;

    return 0;
}
