# password_generator.py
# 🔐 A simple password generator app in Python
# Generates strong random passwords with letters, digits, and symbols

import random
import string

def generate_password(length=12):
    """
    Generates a strong random password.
    
    Args:
        length (int): The length of the password (default is 12)
        
    Returns:
        str: A randomly generated password
    """
    if length < 6:
        raise ValueError("Password length should be at least 6 characters")

    # Combine letters, digits, and special characters
    all_chars = string.ascii_letters + string.digits + string.punctuation

    # Use random.choices to select characters
    password = ''.join(random.choices(all_chars, k=length))

    return password


if __name__ == "__main__":
    print("🔐 Password Generator App 🔐")
    try:
        length = int(input("Enter the desired password length: "))
        generated_password = generate_password(length)
        print(f"\nYour strong password is: {generated_password}")
    except ValueError as e:
        print(f"Error: {e}")
