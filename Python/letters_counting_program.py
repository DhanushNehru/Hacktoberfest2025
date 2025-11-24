def count_letters(text):
    count = {}
    for ch in text:
        if ch.isalpha():
            ch = ch.lower()
            count[ch] = count.get(ch, 0) + 1
    return count


if __name__ == "__main__":
    text = input("Enter text: ")
    result = count_letters(text)
    print("\nLetter counts:")
    for letter, freq in sorted(result.items()):
        print(f"{letter}: {freq}")
