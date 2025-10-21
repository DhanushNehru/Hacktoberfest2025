#include <iostream>
using namespace std;

int main() {
    int score = 0;
    char ans;

    cout << "🧠 Simple Quiz Game\n";

    cout << "1. Capital of India?\nA) Delhi\nB) Mumbai\nC) Kolkata\n";
    cin >> ans;
    if (ans == 'A' || ans == 'a') score++;

    cout << "2. 2 + 2 * 2 = ?\nA) 8\nB) 6\nC) 4\n";
    cin >> ans;
    if (ans == 'B' || ans == 'b') score++;

    cout << "3. Founder of C++?\nA) James Gosling\nB) Bjarne Stroustrup\nC) Dennis Ritchie\n";
    cin >> ans;
    if (ans == 'B' || ans == 'b') score++;

    cout << "\nYour score: " << score << "/3\n";
    return 0;
}
