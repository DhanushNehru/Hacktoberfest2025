#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int chooseDifficulty() {
    int choice;
    cout << "Choose difficulty level:\n";
    cout << "1. Easy (10 tries)\n2. Medium (7 tries)\n3. Hard (5 tries)\n";
    cout << "Enter choice (1-3): ";
    while (true) {
        cin >> choice;
        if (choice >= 1 && choice <= 3) return choice;
        cout << "Invalid choice, please enter 1, 2, or 3: ";
    }
}

int maxTriesForDifficulty(int diff) {
    switch(diff) {
        case 1: return 10;
        case 2: return 7;
        case 3: return 5;
        default: return 7; // by default, if any other input is given, choose medium difficulty
    }
}

void playGame() {
    int difficulty = chooseDifficulty();
    int maxTries = maxTriesForDifficulty(difficulty);
    int secretNumber = rand() % 100 + 1;
    int guess;
    int tries = 0;

    cout << "Guess the number between 1 and 100. You have " << maxTries << " tries.\n";

    while (tries < maxTries) {
        cout << "Attempt " << (tries + 1) << ": ";
        cin >> guess;

        if (guess < 1 || guess > 100) {
            cout << "Please enter a number between 1 and 100.\n";
            continue; // do not count the invalid attempts
        }

        tries++;

        if (guess == secretNumber) {
            cout << "Congratulations! You guessed the number in " << tries << " tries.\n";
            return;
        } else if (guess < secretNumber) {
            cout << "Too low! Try a higher number.\n";
        } else {
            cout << "Too high! Try a lower number.\n";
        }
    }
    cout << "Sorry, you've used all your tries. The number was: " << secretNumber << "\n";
}

int main() {
    srand(time(0));  // changes the seed every time a new game is played

    char playAgain;
    do {
        playGame();
        cout << "Do you want to play again? (y/n): ";
        cin >> playAgain;
    } while (playAgain == 'y' || playAgain == 'Y');

    cout << "Thank you for playing!\n";
    return 0;
}
