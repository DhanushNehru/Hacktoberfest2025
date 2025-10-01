import random

def random_sentence():
    sentences = [
        "You are capable of amazing things 🌟",
        "Every day is a fresh start 🌱",
        "Happiness is found in the little things ✨",
        "Your smile can brighten someone’s day 😊",
        "Believe in yourself and all that you are 💪",
        "Kindness is free—sprinkle it everywhere 💖",
        "Dream big, work hard, stay humble 🌍",
        "The best time for new beginnings is now 🌸"
    ]
    return random.choice(sentences)

if __name__ == "__main__":
    print(random_sentence())