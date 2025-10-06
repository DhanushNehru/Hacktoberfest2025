def is_anagram(str1, str2):
    # Remove spaces and convert to lowercase
    str1 = str1.replace(" ", "").lower()
    str2 = str2.replace(" ", "").lower()
    
    # Check if the lengths are the same
    if len(str1) != len(str2):
        return False
    
    # Create dictionaries to store character counts
    char_count1 = {}
    char_count2 = {}
    
    # Count occurrences of each character in both strings
    for char in str1:
        char_count1[char] = char_count1.get(char, 0) + 1
    
    for char in str2:
        char_count2[char] = char_count2.get(char, 0) + 1
    
    # Compare the character counts
    return char_count1 == char_count2

# Test the function
if __name__ == "__main__":
    # Test cases
    print(is_anagram("listen", "silent"))  # True
    print(is_anagram("hello", "world"))    # False
    print(is_anagram("anagram", "nag a ram"))  # True
    print(is_anagram("keeper", "peeker"))  # True
    print(is_anagram("state", "taste"))    # True
    print(is_anagram("python", "typhon"))  # True
    print(is_anagram("python", "java"))    # False