"""
Motivational Quote Notifier 🎯
-----------------------------
A simple command-line program that displays random motivational quotes,
allows saving them as favorites, and lets users view all saved quotes later.

Features:
- Randomly displays motivational quotes.
- Option to save favorite quotes to a file.
- View all previously saved quotes anytime.
"""

import random
import os

# List of motivational quotes
QUOTES = [
    "Believe in yourself and all that you are.",
    "Your limitation—it's only your imagination.",
    "Push yourself, because no one else is going to do it for you.",
    "Sometimes later becomes never. Do it now.",
    "Great things never come from comfort zones.",
    "Dream it. Wish it. Do it.",
    "Success doesn't just find you. You have to go out and get it.",
    "Don't stop when you're tired. Stop when you're done.",
    "Wake up with determination. Go to bed with satisfaction.",
    "Little things make big days."
]

# File name where favorite quotes are saved
FAVORITES_FILE = "favorites.txt"


def display_quote():
    """
    Display a random motivational quote.
    Ask the user if they want to save it to their favorites.
    """
    # Choose a random quote from the list
    quote = random.choice(QUOTES)
    print("\n💡 Motivational Quote of the Day:\n")
    print(f"\"{quote}\"\n")

    # Ask the user if they want to save it
    save = input("Do you want to save this quote to favorites? (y/n): ").strip().lower()
    if save == "y":
        save_quote(quote)


def save_quote(quote: str):
    """
    Save a selected quote into the favorites file.
    Creates the file if it doesn’t exist.
    """
    with open(FAVORITES_FILE, "a") as f:
        f.write(quote + "\n")
    print("✅ Quote saved to favorites!")


def view_favorites():
    """
    Display all favorite quotes saved by the user.
    If no favorites are found, show a helpful message.
    """
    if not os.path.exists(FAVORITES_FILE):
        print("❌ No favorites found. Save some quotes first!")
        return

    print("\n📚 Your Favorite Quotes:\n")
    with open(FAVORITES_FILE, "r") as f:
        # Enumerate lines to show numbered list of favorites
        for idx, line in enumerate(f.readlines(), start=1):
            print(f"{idx}. {line.strip()}")


def main():
    """
    Main function that displays the menu and handles user choices.
    """
    while True:
        print("\n=== Motivational Quote Notifier ===")
        print("1. Show random quote")
        print("2. View favorite quotes")
        print("3. Exit")

        choice = input("Choose an option (1/2/3): ").strip()

        if choice == "1":
            display_quote()
        elif choice == "2":
            view_favorites()
        elif choice == "3":
            print("Goodbye! Stay motivated 💪")
            break
        else:
            print("⚠️ Invalid choice! Please select 1, 2, or 3.")


# Run the program only if this script is executed directly
if __name__ == "__main__":
    main()
