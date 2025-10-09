# reverse_string.py
# Program to reverse a string

def reverse_string(s):
    return s[::-1]

if __name__ == "__main__":
    text = input("Enter a string: ")
    print("Reversed string:", reverse_string(text))
