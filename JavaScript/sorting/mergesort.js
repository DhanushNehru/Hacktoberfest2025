// Merge Sort in JavaScript
// Author: Sai Surya

function mergeSort(arr) {
    if (arr.length < 2) return arr;
    let mid = Math.floor(arr.length / 2);
    let left = mergeSort(arr.slice(0, mid));
    let right = mergeSort(arr.slice(mid));
    return merge(left, right);
}

function merge(left, right) {
    let result = [];
    while (left.length && right.length) {
        if (left[0] <= right[0]) result.push(left.shift());
        else result.push(right.shift());
    }
    return result.concat(left, right);
}

// Test
let arr4 = [38, 27, 43, 3, 9, 82, 10];
console.log("Merge Sorted:", mergeSort(arr4));
