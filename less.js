function twoSum(arr, target)
{
    arr.sort((a, b) => a - b);

    let left = 0, right = arr.length - 1;

    // Iterate while left pointer is less than right
    while (left < right) {
        let sum = arr[left] + arr[right];

        // Check if the sum matches the target
        if (sum === target)
            return true;
        else if (sum < target)
        
        // Move left pointer to the right
            left++; 
        else
        
        // Move right pointer to the left
            right--; 
    }
    // If no pair is found
    return false;
}

// Driver Code
let arr = [ 0, -1, 2, -3, 1 ];
let target = -2;

if (twoSum(arr, target)) {
    console.log("true");
} else {
    console.log("false");
}
