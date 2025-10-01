function swap(arr, i, j) {
    [arr[i], arr[j]] = [arr[j], arr[i]];
}

function copyArray(arr) {
    return [...arr];
}

function bubbleSort(arr) {
    const result = copyArray(arr);
    const n = result.length;
    
    for (let i = 0; i < n - 1; i++) {
        let swapped = false;
        for (let j = 0; j < n - i - 1; j++) {
            if (result[j] > result[j + 1]) {
                swap(result, j, j + 1);
                swapped = true;
            }
        }
        if (!swapped) break;
    }
    
    return result;
}

function selectionSort(arr) {
    const result = copyArray(arr);
    const n = result.length;
    
    for (let i = 0; i < n - 1; i++) {
        let minIndex = i;
        for (let j = i + 1; j < n; j++) {
            if (result[j] < result[minIndex]) {
                minIndex = j;
            }
        }
        if (minIndex !== i) {
            swap(result, i, minIndex);
        }
    }
    
    return result;
}

function insertionSort(arr) {
    const result = copyArray(arr);
    
    for (let i = 1; i < result.length; i++) {
        const key = result[i];
        let j = i - 1;
        
        while (j >= 0 && result[j] > key) {
            result[j + 1] = result[j];
            j--;
        }
        result[j + 1] = key;
    }
    
    return result;
}

function mergeSort(arr) {
    if (arr.length <= 1) return copyArray(arr);
    
    const mid = Math.floor(arr.length / 2);
    const left = mergeSort(arr.slice(0, mid));
    const right = mergeSort(arr.slice(mid));
    
    return merge(left, right);
}

function merge(left, right) {
    const result = [];
    let i = 0, j = 0;
    
    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            result.push(left[i]);
            i++;
        } else {
            result.push(right[j]);
            j++;
        }
    }
    
    return result.concat(left.slice(i)).concat(right.slice(j));
}

function quickSort(arr) {
    if (arr.length <= 1) return copyArray(arr);
    
    const result = copyArray(arr);
    quickSortHelper(result, 0, result.length - 1);
    return result;
}

function quickSortHelper(arr, low, high) {
    if (low < high) {
        const pivotIndex = partition(arr, low, high);
        quickSortHelper(arr, low, pivotIndex - 1);
        quickSortHelper(arr, pivotIndex + 1, high);
    }
}

function partition(arr, low, high) {
    const pivot = arr[high];
    let i = low - 1;
    
    for (let j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return i + 1;
}

function heapSort(arr) {
    const result = copyArray(arr);
    const n = result.length;
    
    for (let i = Math.floor(n / 2) - 1; i >= 0; i--) {
        heapify(result, n, i);
    }
    
    for (let i = n - 1; i > 0; i--) {
        swap(result, 0, i);
        heapify(result, i, 0);
    }
    
    return result;
}

function heapify(arr, n, i) {
    let largest = i;
    const left = 2 * i + 1;
    const right = 2 * i + 2;
    
    if (left < n && arr[left] > arr[largest]) {
        largest = left;
    }
    
    if (right < n && arr[right] > arr[largest]) {
        largest = right;
    }
    
    if (largest !== i) {
        swap(arr, i, largest);
        heapify(arr, n, largest);
    }
}

if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        bubbleSort,
        selectionSort,
        insertionSort,
        mergeSort,
        quickSort,
        heapSort,
        swap,
        copyArray
    };
}