def bubble_sort(list_):
    n = len(list_)
    for i in range(n):
        for j in range(0, n - i - 1):
            if list_[j] > list_[j + 1]:
                list_[j], list_[j + 1] = list_[j + 1], list_[j]

# Example
my_list = [64, 34, 25, 12, 22, 11, 90]
bubble_sort(my_list)
print("Sorted list is:", my_list)