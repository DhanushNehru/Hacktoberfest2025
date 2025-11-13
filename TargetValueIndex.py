def search_insert_position(nums, target):
    """
    Given a sorted array of distinct integers and a target value,
    return the index if the target is found. If not, return the index
    where it would be if it were inserted in order.

    """
    left, right = 0, len(nums) - 1

    while left <= right:
        mid = (left + right) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            left = mid + 1
        else:
            right = mid - 1

    return left

if __name__ == "__main__":
    n = int(input("Enter the length of the array: "))
    num = list(map(int, input("Enter the sorted array elements: ").split()))
    target = int(input("Enter the target value: "))
    result = search_insert_position(num, target)
    print(f"The target value should be at index: {result}")