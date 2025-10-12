// Radix Sort in JavaScript
// Author: Sai Surya

function radixSort(arr) {
    let max = Math.max(...arr);
    let exp = 1;

    while (Math.floor(max / exp) > 0) {
        countingSortByDigit(arr, exp);
        exp *= 10;
    }
    return arr;
}

function countingSortByDigit(arr, exp) {
    let n = arr.length;
    let output = Array(n).fill(0);
    let count = Array(10).fill(0);

    for (let i = 0; i < n; i++) count[Math.floor(arr[i] / exp) % 10]++;
    for (let i = 1; i < 10; i++) count[i] += count[i - 1];

    for (let i = n - 1; i >= 0; i--) {
        let index = Math.floor(arr[i] / exp) % 10;
        output[count[index] - 1] = arr[i];
        count[index]--;
    }

    for (let i = 0; i < n; i++) arr[i] = output[i];
}

// Test
let arr6 = [170, 45, 75, 90, 802, 24, 2, 66];
radixSort(arr6);
console.log("Radix Sorted:", arr6);
