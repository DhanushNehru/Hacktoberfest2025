// Counting Sort in JavaScript
// Author: Sai Surya

function countingSort(arr) {
    let max = Math.max(...arr);
    let min = Math.min(...arr);
    let range = max - min + 1;
    let count = Array(range).fill(0);
    let output = Array(arr.length);

    // Store count of each element
    for (let i = 0; i < arr.length; i++) count[arr[i] - min]++;

    // Change count[i] so that count[i] now contains actual position
    for (let i = 1; i < count.length; i++) count[i] += count[i - 1];

    // Build output array
    for (let i = arr.length - 1; i >= 0; i--) {
        output[count[arr[i] - min] - 1] = arr[i];
        count[arr[i] - min]--;
    }

    // Copy to original array
    for (let i = 0; i < arr.length; i++) arr[i] = output[i];
    return arr;
}

// Test
let arr5 = [4, 2, 2, 8, 3, 3, 1];
console.log("Counting Sorted:", countingSort(arr5));
