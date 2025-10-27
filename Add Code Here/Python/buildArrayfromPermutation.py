def buildArray(nums: list[int]) -> list[int]:
    """
    Builds an array ans of the same length as nums where ans[i] = nums[nums[i]].
    nums is a zero-based permutation.
    """
    n = len(nums)
    # Initialize the result array 'ans' with the correct length.
    ans = [0] * n
    
    # Iterate through each index i from 0 to n-1.
    for i in range(n):
        # The key operation: ans[i] = nums[nums[i]]
        # 1. Get the value at nums[i]. This value is the *new* index.
        new_index = nums[i]
        
        # 2. Use the new_index to look up the final value in nums.
        final_value = nums[new_index]
        
        # 3. Store the final value in the result array ans[i].
        ans[i] = final_value
        
    return ans

# ---

## Example Usage

# Example 1
nums1 = [0, 2, 1, 5, 3, 4]
result1 = buildArray(nums1)
print(f"Input: {nums1}")
print(f"Output: {result1}")
# Expected Output: [0, 1, 2, 4, 5, 3]

# Example 2
nums2 = [5, 0, 1, 2, 3, 4]
result2 = buildArray(nums2)
print(f"Input: {nums2}")
print(f"Output: {result2}")
# Expected Output: [4, 5, 0, 1, 2, 3]