import random
import string
import os

class HangmanGame:
    """Classic Hangman word guessing game"""
    
    # Hangman ASCII art stages
    HANGMAN_STAGES = [
        # Stage 0 - start
        """
           ------
           |    |
           |
           |
           |
           |
        --------
        """,
        # Stage 1 - head
        """
           ------
           |    |
           |    O
           |
           |
           |
        --------
        """,
        # Stage 2 - body
        """
           ------
           |    |
           |    O
           |    |
           |
           |
        --------
        """,
        # Stage 3 - left arm
        """
           ------
           |    |
           |    O
           |   /|
           |
           |
        --------
        """,
        # Stage 4 - right arm
        """
           ------
           |    |
           |    O
           |   /|\\
           |
           |
        --------
        """,
        # Stage 5 - left leg
        """
           ------
           |    |
           |    O
           |   /|\\
           |   /
           |
        --------
        """,
        # Stage 6 - right leg (game over)
        """
           ------
           |    |
           |    O
           |   /|\\
           |   / \\
           |
        --------
        """
    ]
    
    # Word categories
    WORD_LISTS = {
        'animals': ['elephant', 'giraffe', 'penguin', 'dolphin', 'kangaroo', 
                    'cheetah', 'zebra', 'leopard', 'rhinoceros', 'hippopotamus'],
        'countries': ['brazil', 'japan', 'egypt', 'canada', 'australia', 
                      'france', 'mexico', 'india', 'norway', 'argentina'],
        'fruits': ['banana', 'orange', 'strawberry', 'pineapple', 'watermelon',
                   'mango', 'blueberry', 'raspberry', 'coconut', 'papaya'],
        'sports': ['basketball', 'football', 'tennis', 'swimming', 'cricket',
                   'volleyball', 'baseball', 'hockey', 'badminton', 'golf'],
        'programming': ['python', 'javascript', 'algorithm', 'function', 'variable',
                        'database', 'compiler', 'debugging', 'interface', 'recursion']
    }
    
    def __init__(self, max_attempts=6):
        """Initialize the game"""
        self.max_attempts = max_attempts
        self.attempts_left = max_attempts
        self.word = ""
        self.category = ""
        self.guessed_letters = set()
        self.correct_letters = set()
        self.word_progress = []
    
    def clear_screen(self):
        """Clear the console screen"""
        os.system('cls' if os.name == 'nt' else 'clear')
    
    def choose_word(self, category=None):
        """Choose a random word from a category"""
        if category and category in self.WORD_LISTS:
            self.category = category
        else:
            self.category = random.choice(list(self.WORD_LISTS.keys()))
        
        self.word = random.choice(self.WORD_LISTS[self.category]).upper()
        self.word_progress = ['_' for _ in self.word]
    
    def display_game(self):
        """Display the current game state"""
        print(self.HANGMAN_STAGES[self.max_attempts - self.attempts_left])
        print(f"\nCategory: {self.category.capitalize()}")
        print(f"Word: {' '.join(self.word_progress)}")
        print(f"\nAttempts remaining: {self.attempts_left}")
        print(f"Guessed letters: {', '.join(sorted(self.guessed_letters)) if self.guessed_letters else 'None'}")
    
    def get_guess(self):
        """Get a valid letter guess from the player"""
        while True:
            guess = input("\nGuess a letter: ").upper().strip()
            
            if len(guess) != 1:
                print("Please enter a single letter.")
                continue
            
            if guess not in string.ascii_uppercase:
                print("Please enter a valid letter (A-Z).")
                continue
            
            if guess in self.guessed_letters:
                print("You already guessed that letter!")
                continue
            
            return guess
    
    def process_guess(self, guess):
        """Process the player's guess and update game state"""
        self.guessed_letters.add(guess)
        
        if guess in self.word:
            self.correct_letters.add(guess)
            # Update word progress
            for i, letter in enumerate(self.word):
                if letter == guess:
                    self.word_progress[i] = guess
            return True
        else:
            self.attempts_left -= 1
            return False
    
    def is_won(self):
        """Check if the player has won"""
        return '_' not in self.word_progress
    
    def is_lost(self):
        """Check if the player has lost"""
        return self.attempts_left <= 0
    
    def play(self):
        """Main game loop"""
        self.clear_screen()
        print("=" * 50)
        print("HANGMAN GAME".center(50))
        print("=" * 50)
        
        # Choose category
        print("\nCategories:")
        categories = list(self.WORD_LISTS.keys())
        for i, cat in enumerate(categories, 1):
            print(f"{i}. {cat.capitalize()}")
        print(f"{len(categories) + 1}. Random")
        
        choice = input(f"\nChoose a category (1-{len(categories) + 1}): ").strip()
        
        if choice.isdigit() and 1 <= int(choice) <= len(categories):
            self.choose_word(categories[int(choice) - 1])
        else:
            self.choose_word()
        
        # Game loop
        while True:
            self.clear_screen()
            self.display_game()
            
            if self.is_won():
                print("\n" + "=" * 50)
                print("🎉 CONGRATULATIONS! YOU WON! 🎉".center(50))
                print("=" * 50)
                print(f"\nThe word was: {self.word}")
                print(f"You guessed it with {self.attempts_left} attempts remaining!")
                break
            
            if self.is_lost():
                print("\n" + "=" * 50)
                print("💀 GAME OVER! 💀".center(50))
                print("=" * 50)
                print(f"\nThe word was: {self.word}")
                break
            
            guess = self.get_guess()
            correct = self.process_guess(guess)
            
            if correct:
                print(f"\n✓ Correct! '{guess}' is in the word.")
            else:
                print(f"\n✗ Wrong! '{guess}' is not in the word.")
            
            input("\nPress Enter to continue...")
    
    def get_statistics(self):
        """Return game statistics"""
        return {
            'attempts_used': self.max_attempts - self.attempts_left,
            'correct_guesses': len(self.correct_letters),
            'wrong_guesses': len(self.guessed_letters) - len(self.correct_letters),
            'total_guesses': len(self.guessed_letters)
        }


def main():
    """Main function to run the Hangman game"""
    while True:
        game = HangmanGame()
        game.play()
        
        # Play again?
        print("\n" + "=" * 50)
        play_again = input("\nPlay again? (y/n): ").lower().strip()
        
        if play_again != 'y':
            print("\nThanks for playing Hangman! Goodbye! 👋")
            break


if __name__ == "__main__":
    main()
