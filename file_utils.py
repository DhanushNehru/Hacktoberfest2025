"""
file_utils.py

A collection of file handling utility functions in Python.
Useful for reading, writing, and working with text files.

Author: Soumya
Hacktoberfest 2025 Contribution
"""

from typing import List


def read_file(filename: str) -> str:
    """
    Read the entire content of a file.

    Args:
        filename (str): Path to the file.

    Returns:
        str: File content.
    """
    with open(filename, "r", encoding="utf-8") as f:
        return f.read()


def write_file(filename: str, content: str) -> None:
    """
    Write content to a file (overwrites existing content).

    Args:
        filename (str): Path to the file.
        content (str): Text to write.
    """
    with open(filename, "w", encoding="utf-8") as f:
        f.write(content)


def append_file(filename: str, content: str) -> None:
    """
    Append content to a file.

    Args:
        filename (str): Path to the file.
        content (str): Text to append.
    """
    with open(filename, "a", encoding="utf-8") as f:
        f.write(content)


def read_lines(filename: str) -> List[str]:
    """
    Read all lines from a file.

    Args:
        filename (str): Path to the file.

    Returns:
        List[str]: List of lines from the file.
    """
    with open(filename, "r", encoding="utf-8") as f:
        return f.readlines()


def count_words(filename: str) -> int:
    """
    Count total words in a text file.

    Args:
        filename (str): Path to the file.

    Returns:
        int: Number of words in the file.
    """
    text = read_file(filename)
    return len(text.split())


if __name__ == "__main__":
    # Example usage (creates a test.txt file)
    write_file("test.txt", "Hello Hacktoberfest!\n")
    append_file("test.txt", "This is a file utility demo.\n")

    print("File Content:\n", read_file("test.txt"))
    print("File Lines:", read_lines("test.txt"))
    print("Word Count:", count_words("test.txt"))
