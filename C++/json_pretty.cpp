#include <bits/stdc++.h>
using namespace std;

/*
 * json_pretty.cpp
 *
 * A simple JSON-like pretty printer and validator.
 * Usage:
 *   ./json_pretty < input.json
 *
 * Features:
 * - Reads JSON text from stdin.
 * - Checks for balanced braces/brackets.
 * - Pretty-prints with indentation.
 */

bool isValidJSON(const string &s) {
    stack<char> st;
    for (char c : s) {
        if (c == '{' || c == '[') st.push(c);
        else if (c == '}') {
            if (st.empty() || st.top() != '{') return false;
            st.pop();
        } else if (c == ']') {
            if (st.empty() || st.top() != '[') return false;
            st.pop();
        }
    }
    return st.empty();
}

void prettyPrintJSON(const string &s, int indentSize = 4) {
    int level = 0;
    bool inString = false;

    for (size_t i = 0; i < s.size(); ++i) {
        char c = s[i];

        if (c == '\"') {
            cout << c;
            inString = !inString;
        } 
        else if (!inString && (c == '{' || c == '[')) {
            cout << c << "\n";
            level++;
            cout << string(level * indentSize, ' ');
        } 
        else if (!inString && (c == '}' || c == ']')) {
            cout << "\n";
            level--;
            cout << string(level * indentSize, ' ') << c;
        } 
        else if (!inString && c == ',') {
            cout << c << "\n" << string(level * indentSize, ' ');
        } 
        else if (!inString && (c == ':')) {
            cout << ": ";
        } 
        else if (!isspace((unsigned char)c)) {
            cout << c;
        }
    }
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string input((istreambuf_iterator<char>(cin)), istreambuf_iterator<char>());

    if (input.empty()) {
        cerr << "Error: No input provided.\n";
        return 1;
    }

    if (!isValidJSON(input)) {
        cerr << "Invalid JSON structure (unbalanced braces/brackets).\n";
        return 2;
    }

    cout << "✅ JSON is valid.\n";
    cout << "Pretty-printed output:\n";
    prettyPrintJSON(input);

    return 0;
}
