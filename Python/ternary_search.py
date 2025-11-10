"""
Ternary Search Algorithm
------------------------
This algorithm divides the search interval into three parts and determines which part contains the key element.
It works efficiently for sorted arrays.

Time Complexity: O(log3 N)
Space Complexity: O(1)
"""

def ternary_search(arr, target):
    """
    Perform ternary search on a sorted list.

    Parameters:
        arr (list): Sorted list of elements
        target (int/float): Value to be searched

    Returns:
        int: Index of target if found, otherwise -1
    """
    left, right = 0, len(arr) - 1

    while left <= right:
        # Divide the array into three parts
        mid1 = left + (right - left) // 3
        mid2 = right - (right - left) // 3

        if arr[mid1] == target:
            return mid1
        if arr[mid2] == target:
            return mid2

        if target < arr[mid1]:
            right = mid1 - 1
        elif target > arr[mid2]:
            left = mid2 + 1
        else:
            left = mid1 + 1
            right = mid2 - 1

    return -1


if __name__ == "__main__":
    arr = [2, 4, 10, 14, 18, 21, 25, 30]
    target = 21
    result = ternary_search(arr, target)
    print(f"Array: {arr}")
    print(f"Target: {target}")
    print(f"Result: {result if result != -1 else 'Not Found'}")
