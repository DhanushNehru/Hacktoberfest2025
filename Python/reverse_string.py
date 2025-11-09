def reverse_string(s):
    """
    Reverses the input string and returns it.
    """
    return s[::-1]

# User input
text = input("Enter a string to reverse: ")
print("Reversed string:", reverse_string(text))

# Test cases
assert reverse_string("hello") == "olleh"
assert reverse_string("Krish") == "hsirK"
assert reverse_string("12345") == "54321"
