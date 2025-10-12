#include <iostream>
#include <stack>
#include <sstream>
#include <string>
using namespace std;

// Function to check if a character is an operator
bool isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/');
}

// Function to convert postfix expression to infix
string postfixToInfix(const string& postfix) {
    stack<string> s;
    stringstream ss(postfix);
    string token;

    while (ss >> token) {
        if (token.length() == 1 && isOperator(token[0])) {
            // Operator: pop two operands
            if (s.size() < 2) {
                cerr << "Invalid postfix expression!" << endl;
                return "";
            }
            string op2 = s.top(); s.pop();
            string op1 = s.top(); s.pop();
            string expr = "(" + op1 + " " + token + " " + op2 + ")";
            s.push(expr);
        } else {
            // Operand: push to stack
            s.push(token);
        }
    }

    if (s.size() != 1) {
        cerr << "Invalid postfix expression!" << endl;
        return "";
    }

    return s.top();
}

int main() {
    string postfixExpr;
    cout << "Enter a postfix expression (space-separated): ";
    getline(cin, postfixExpr);

    string infixExpr = postfixToInfix(postfixExpr);

    if (!infixExpr.empty())
        cout << "Converted Infix Expression: " << infixExpr << endl;

    return 0;
}
