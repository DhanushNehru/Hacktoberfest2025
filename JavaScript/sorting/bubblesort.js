// Bubble Sort in JavaScript
// Author: Sai Surya

function bubbleSort(arr) {
    let n = arr.length;
    for (let i = 0; i < n - 1; i++) {
        for (let j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j+1]
                [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
            }
        }
    }
    return arr;
}

// Test
let arr1 = [64, 34, 25, 12, 22, 11, 90];
console.log("Bubble Sorted:", bubbleSort(arr1));
