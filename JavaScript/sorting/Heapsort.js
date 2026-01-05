// Quick Sort Algorithm in JavaScript
// Author: Sai Surya

function quickSort(arr, low = 0, high = arr.length - 1) {
    if (low < high) {
        // Partition the array around the pivot
        let pivotIndex = partition(arr, low, high);

        // Recursively sort elements before and after partition
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }
}

// Function to partition the array
function partition(arr, low, high) {
    let pivot = arr[high];  // Choose last element as pivot
    let i = low - 1;        // Pointer for smaller elements

    for (let j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            // Swap arr[i] and arr[j]
            [arr[i], arr[j]] = [arr[j], arr[i]];
        }
    }

    // Place pivot in correct position
    [arr[i + 1], arr[high]] = [arr[high], arr[i + 1]];
    return i + 1; // Return pivot index
}

// Test the quickSort function
let arr = [10, 7, 8, 9, 1, 5];
quickSort(arr);
console.log("Sorted array is:", arr);
