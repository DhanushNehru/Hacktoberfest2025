 //// Find second largest in an array.

// 1st approach - JavaScript program to find the second largest element in the array
// using two traversals

function getSecondLargest(arr) {
  let n = arr.length;

  let largest = -1,
    secondLargest = -1;

  // Finding the largest element
  for (let i = 0; i < n; i++) {
    if (arr[i] > largest) largest = arr[i];
  }

  // Finding the second largest element
  for (let i = 0; i < n; i++) {
    // Update second largest if the current element is greater
    // than second largest and not equal to the largest
    if (arr[i] > secondLargest && arr[i] !== largest) {
      secondLargest = arr[i];
    }
  }
  return secondLargest;
}

let arr = [12, 35, 1, 10, 34, 1];
console.log(getSecondLargest(arr));

// 2nd approach -  JavaScript program to find the second largest element in the array
// using one traversal

// function to find the second largest element in the array
function getSecondLargest(arr1) {
  const n = arr1.length;

  let largest = -1,
    secondLargest = -1;

  // finding the second largest element
  for (let i = 0; i < n; i++) {
    // If arr[i] > largest, update second largest with
    // largest and largest with arr[i]
    if (arr1[i] > largest) {
      secondLargest = largest;
      largest = arr1[i];
    }

    // If arr[i] < largest and arr[i] > second largest,
    // update second largest with arr[i]
    else if (arr1[i] < largest && arr1[i] > secondLargest) {
      secondLargest = arr1[i];
    }
  }
  return secondLargest;
}

const arr1 = [12, 35, 1, 10, 34, 1];
console.log(getSecondLargest(arr1));
