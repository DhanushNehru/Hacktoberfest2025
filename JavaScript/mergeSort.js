function merge(left, right) {
  const result = [];
  let i = 0;
  let j = 0;

  s;
  while (i < left.length && j < right.length) {
    if (left[i] < right[j]) {
      result.push(left[i]);
      i++;
    } else {
      result.push(right[j]);
      j++;
    }
  }

  return result.concat(left.slice(i)).concat(right.slice(j));
}

function mergeSort(arr) {
  if (arr.length <= 1) return arr;

  const middle = Math.floor(arr.length / 2);
  const left = mergeSort(arr.slice(0, middle));
  const right = mergeSort(arr.slice(middle));

  return merge(left, right);
}

const unsorted = [38, 27, 43, 3, 9, 82, 10];
const sorted = mergeSort(unsorted);

console.log("Unsorted:", unsorted);
console.log("Sorted:  ", sorted);
