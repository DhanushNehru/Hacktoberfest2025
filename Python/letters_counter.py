# letters_counter.py
# Program to count the frequency of letters in a string

def count_letters(s):
    freq = {}
    for char in s:
        if char.isalpha():  # only letters
            freq[char] = freq.get(char, 0) + 1
    return freq

if __name__ == "__main__":
    text = input("Enter a string: ")
    result = count_letters(text)
    print("Letter frequencies:", result)
