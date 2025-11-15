"""
password_generator.py

A simple and secure random password generator.
Useful for creating strong passwords for accounts and applications.

Author: Soumya
Hacktoberfest 2025 Contribution
"""

import random
import string


def generate_password(length: int = 12, use_digits: bool = True, use_symbols: bool = True) -> str:
    """
    Generate a random secure password.

    Args:
        length (int): Length of the password (default 12).
        use_digits (bool): Include digits if True.
        use_symbols (bool): Include special characters if True.

    Returns:
        str: Generated password.
    """
    characters = string.ascii_letters
    if use_digits:
        characters += string.digits
    if use_symbols:
        characters += string.punctuation

    if length < 4:
        raise ValueError("Password length should be at least 4 characters.")

    return ''.join(random.choice(characters) for _ in range(length))


if __name__ == "__main__":
    # Example usage
    print("Password (12 chars, with digits & symbols):", generate_password(12))
    print("Password (16 chars, letters only):", generate_password(16, use_digits=False, use_symbols=False))
    print("Password (20 chars, with digits only):", generate_password(20, use_symbols=False))
