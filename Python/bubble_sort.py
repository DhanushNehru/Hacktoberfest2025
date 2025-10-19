def bubble_sort(arr):
    """
    Sorts a list in ascending order using the Bubble Sort algorithm.

    :param arr: The list of elements to be sorted.
    """
    n = len(arr)

    # Outer loop for each pass
    # We need n-1 passes in the worst case
    for i in range(n - 1):
        # A flag to check if any swap happened in this pass
        swapped = False

        # Inner loop for comparisons
        # After 'i' passes, the last 'i' elements are already in place
        # So, we only need to compare up to (n - 1 - i)
        for j in range(0, n - i - 1):

            # Compare adjacent elements
            if arr[j] > arr[j + 1]:
                # Swap them if they are in the wrong order
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                swapped = True

        # OPTIMIZATION:
        # If no two elements were swapped by the inner loop,
        # then the list is already sorted, and we can stop early.
        if not swapped:
            break

def print_list(arr):
    """Helper function to print the elements of a list."""
    for val in arr:
        print(val, end=" ")
    print() # for a newline

# --- Main function to run the example ---
if __name__ == "__main__":
    # Initialize a list of integers
    data = [64, 34, 25, 12, 22, 11, 90]

    print("Original list:")
    print_list(data)

    # Call the bubble_sort function
    bubble_sort(data)

    print("\nSorted list:")
    print_list(data)
