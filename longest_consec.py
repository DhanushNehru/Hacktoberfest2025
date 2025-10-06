def longest_consecutive(nums: list[int]) -> int:
    """
    Returns the length of the longest consecutive elements sequence in the list.
    """
    if not nums:
        return 0

    num_set = set(nums)
    max_len = 0

    for num in num_set:
        # Only start counting if it's the start of a sequence
        if num - 1 not in num_set:
            current = num
            current_len = 1

            while current + 1 in num_set:
                current += 1
                current_len += 1

            max_len = max(max_len, current_len)

    return max_len

if __name__ == "__main__":
    test_cases = [
        [100, 4, 200, 1, 3, 2],
        [0, -1, 1, 2, -2, 3],
        [9, 1, 4, 7, 3, 2, 6, 8]
    ]

    for lst in test_cases:
        print(f"List: {lst} -> Longest Consecutive Length: {longest_consecutive(lst)}")
