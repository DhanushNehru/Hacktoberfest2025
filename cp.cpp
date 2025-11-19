#include <iostream>
#include <string>
using namespace std;

int main() {
    string str;
    cout << "Enter a string: ";
    getline(cin, str);

    string reversed = string(str.rbegin(), str.rend());
    cout << "Reversed string: " << reversed << endl;

    return 0;
}
