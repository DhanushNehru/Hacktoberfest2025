# linear_search.py

def linear_search(arr, target):
    """
    Performs a linear search on a list to find the index of a target value.

    Parameters:
    arr (list): The list of elements to search through.
    target (any): The value to search for.

    Returns:
    int: The index of the target if found, otherwise -1.
    """
    for index, value in enumerate(arr):
        if value == target:
            return index
    return -1

# Example usage
if __name__ == "__main__":
    data = [10, 23, 45, 70, 11, 15]
    target = 70

    result = linear_search(data, target)

    if result != -1:
        print(f"Element found at index {result}")
    else:
        print("Element not found in the list")
