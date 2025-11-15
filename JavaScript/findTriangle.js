// Solution of GFG findTriangle by Tejsva pandey

class Solution {
    subarraySum(arr, target) {
        let start = 0;
        let sum = 0;
        
        for (let end = 0; end < arr.length; end++) {
            sum += arr[end]; // add current element to window
            
            // Shrink window from left if sum is too large
            while (sum > target && start < end) {
                sum -= arr[start];
                start++;
            }
            
            // Found the target sum
            if (sum === target) {
                return [start + 1, end + 1]; // 1-based index
            }
        }
        
        return [-1];
    }
}
