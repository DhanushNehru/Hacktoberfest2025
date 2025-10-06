from collections import deque

class Solution:
    def maxSlidingWindow(self, nums, k):
        if not nums or k <= 0:
            return []

        n = len(nums)
        result = []
        dq = deque()  # To store indices of the elements in nums

        for i in range(n):
            # Remove indices that are out of this window
            if dq and dq[0] < i - k + 1:
                dq.popleft()

            # Remove elements from the back that are less than the current element
            while dq and nums[dq[-1]] < nums[i]:
                dq.pop()

            # Add the current element's index to the deque
            dq.append(i)

            # The front of the deque is the largest element for the current window
            if i >= k - 1:
                result.append(nums[dq[0]])

        return result
