def caesar_cipher(text, shift, mode='encrypt'):
    result = ''
    if mode == 'decrypt':
        shift = -shift  # reverse the shift for decryption

    for char in text:
        if char.isalpha():
            base = ord('A') if char.isupper() else ord('a')
            result += chr((ord(char) - base + shift) % 26 + base)
        else:
            result += char
    return result


# Main program
message = input("Enter your message: ")

# Validate shift input
while True:
    try:
        shift_value = int(input("Enter shift value (1-25): "))
        if 1 <= shift_value <= 25:
            break
        else:
            print("Please enter a number between 1 and 25.")
    except ValueError:
        print("Invalid input! Please enter a number.")

mode = input("Do you want to encrypt or decrypt? ").strip().lower()
if mode not in ['encrypt', 'decrypt']:
    mode = 'encrypt'

result = caesar_cipher(message, shift_value, mode)

print(f"\nMode: {mode.capitalize()}")
print("Result:", result)