def quick_sort(arr,partition_ratio=1):
    if(len(arr)<=1):
        return arr
    pivot_idx = int(len(arr)*partition_ratio)
    others = arr[:pivot_idx]+arr[pivot_idx+1:]
    left = [x for x in others if x <= arr[pivot_idx]]
    right = [x for x in others if x > arr[pivot_idx]]

    return quick_sort(left,partition_ratio)+[arr[pivot_idx]]+quick_sort(right,partition_ratio)

if __name__ == '__main__':
    arr = [4,5,7,67,8,11]
    pivot_ratio = 0.5
    print(quick_sort(arr,partition_ratio=pivot_ratio))