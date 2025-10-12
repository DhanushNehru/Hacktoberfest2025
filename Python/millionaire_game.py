def millionaire_game():
    questions = [
        {
            "question": "What is the capital of France?",
            "options": ["A) Berlin", "B) Madrid", "C) Paris", "D) Rome"],
            "answer": "C"
        },
        {
            "question": "Which planet is known as the Red Planet?",
            "options": ["A) Earth", "B) Mars", "C) Jupiter", "D) Venus"],
            "answer": "B"
        },
        {
            "question": "Who wrote 'Romeo and Juliet'?",
            "options": ["A) Charles Dickens", "B) William Shakespeare", "C) Mark Twain", "D) Leo Tolstoy"],
            "answer": "B"
        },
        {
            "question": "What is the largest ocean on Earth?",
            "options": ["A) Atlantic Ocean", "B) Indian Ocean", "C) Arctic Ocean", "D) Pacific Ocean"],
            "answer": "D"
        },
        {
            "question": "What is the chemical symbol for gold?",
            "options": ["A) Au", "B) Ag", "C) Gd", "D) Go"],
            "answer": "A"
        },
        {
            "question": "In which year did the Titanic sink?",
            "options": ["A) 1905", "B) 1912", "C) 1920", "D) 1898"],
            "answer": "B"
        },
        {
            "question": "Which language is primarily spoken in Brazil?",
            "options": ["A) Spanish", "B) Portuguese", "C) French", "D) English"],
            "answer": "B"
        },
        {
            "question": "What is the hardest natural substance on Earth?",
            "options": ["A) Gold", "B) Iron", "C) Diamond", "D) Platinum"],
            "answer": "C"
        },
        {
            "question": "Which element has the atomic number 1?",
            "options": ["A) Oxygen", "B) Hydrogen", "C) Helium", "D) Carbon"],
            "answer": "B"
        },
        {
            "question": "Who painted the Mona Lisa?",
            "options": ["A) Vincent Van Gogh", "B) Pablo Picasso", "C) Leonardo da Vinci", "D) Claude Monet"],
            "answer": "C"
        }
    ]

    prize_levels = [
        "$100", "$200", "$300", "$500", "$1,000",
        "$2,000", "$4,000", "$8,000", "$16,000", "$32,000"
    ]

    print("Welcome to Who Wants to Be a Millionaire!\n")

    for i, q in enumerate(questions):
        print(f"Level {i+1} for {prize_levels[i]}")
        print(q["question"])
        for option in q["options"]:
            print(option)
        
        answer = input("Your answer (A, B, C, or D): ").upper()
        if answer == q["answer"]:
            print("Correct!\n")
        else:
            print(f"Wrong answer! Game over. You won {prize_levels[i-1] if i > 0 else '$0'}.")
            return

    print(f"Congratulations! You answered all questions correctly and won {prize_levels[-1]}!")

if __name__ == "__main__":
    millionaire_game()
