// Selection Sort in JavaScript
// Author: Sai Surya

function selectionSort(arr) {
    let n = arr.length;
    for (let i = 0; i < n - 1; i++) {
        let minIndex = i;
        for (let j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) minIndex = j;
        }
        // Swap arr[i] and arr[minIndex]
        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];
    }
    return arr;
}

// Test
let arr2 = [29, 10, 14, 37, 13];
console.log("Selection Sorted:", selectionSort(arr2));
