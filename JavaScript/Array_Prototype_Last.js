Array.prototype.last = function() {
    if (this.length === 0) {
    console.log("Array is empty");
  } else {
    console.log(`Last element of array is : ${JSON.stringify(this[this.length - 1])}`);
  }
};

const arr=[3,65,87,89]
arr.last()