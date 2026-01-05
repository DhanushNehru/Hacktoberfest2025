# max_element_sliding_window.py
# Hacktoberfest Contribution
# Problem: Find the maximum element in every sliding window of size K

from collections import deque

def max_sliding_window(nums, k):
    """
    Returns a list of maximum values from each sliding window of size k.
    
    Args:
    nums: List[int] - input array
    k: int - size of the sliding window
    
    Returns:
    List[int] - maximum values for each window
    """
    if not nums or k == 0:
        return []

    dq = deque()  # stores indices of elements
    result = []

    for i in range(len(nums)):
        # Remove elements out of the current window
        while dq and dq[0] <= i - k:
            dq.popleft()

        # Remove smaller elements in k range as they are useless
        while dq and nums[dq[-1]] < nums[i]:
            dq.pop()

        # Add current element's index
        dq.append(i)

        # Append current window's maximum
        if i >= k - 1:
            result.append(nums[dq[0]])

    return result


# Example usage
if __name__ == "__main__":
    nums = [1, 3, -1, -3, 5, 3, 6, 7]
    k = 3
    print("Input:", nums, "Window size:", k)
    print("Output:", max_sliding_window(nums, k))
    # Expected Output: [3, 3, 5, 5, 6, 7]
