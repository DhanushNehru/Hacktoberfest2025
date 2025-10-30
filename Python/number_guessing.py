from random import randint

easy_turns = 10
hard_turns = 5

def check_answer(user_guess, actual_answer, turns):
    if user_guess > actual_answer:
        print("Uh oh, Too High...👆🏻")
        return turns - 1
    elif user_guess < actual_answer:
        print("Uh oh, Too Low...👇🏻")
        return turns - 1
    else:
        print("Woohoo! Are you a mind reader!!🤯")
        return turns  

def set_difficulty():
    level = input("Are you a risk taker and go for hard level???? 💪 Or would you like to take it easy...??😚: ").lower()
    if level == "easy":
        return easy_turns
    else:
        return hard_turns

def game():
    print("Welcome to Number Guessing Game!!")
    print("Umm... I am thinking about a number between 1 and 100🤔")
    
    answer = randint(1, 100)
    # print(f"the number is {answer}")  # Uncomment this for testing

    turns = set_difficulty()
    guess = 0

    while guess != answer and turns > 0:
        print(f"\nYou have {turns} attempts remaining to guess the number...")
        guess = int(input("Make a Guess: "))
        turns = check_answer(guess, answer, turns)
        
        if guess == answer:
            return  
        elif turns == 0:
            print(f"\nYou've run out of guesses. The number was {answer}. Better luck next time! 🥺")

game()