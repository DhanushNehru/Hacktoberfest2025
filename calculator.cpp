// Hacktoberfest 2025 - Simple Calculator 🧮
#include <iostream>
using namespace std;

int main() {
    double num1, num2;
    char op;

    cout << "🎉 Hacktoberfest 2025 - Simple Calculator 🎉\n";
    cout << "Enter first number: ";
    cin >> num1;
    cout << "Enter operator (+, -, *, /): ";
    cin >> op;
    cout << "Enter second number: ";
    cin >> num2;

    cout << "\nResult: ";

    switch (op) {
        case '+': cout << num1 + num2; break;
        case '-': cout << num1 - num2; break;
        case '*': cout << num1 * num2; break;
        case '/': 
            if (num2 != 0)
                cout << num1 / num2;
            else
                cout << "Error! Division by zero.";
            break;
        default: cout << "Invalid operator!";
    }

    cout << "\nThanks for coding in Hacktoberfest 2025! 💻\n";
    return 0;
}
