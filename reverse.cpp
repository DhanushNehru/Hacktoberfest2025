#include <iostream>
using namespace std;

int main() {
    int n, reversed_number = 0, remainder;

    std::cout << "Enter an integer: ";
    std::cin >> n;

    // Loop until the number becomes 0
    while (n != 0) {
        // Get the last digit
        remainder = n % 10;
        
        // Build the reversed number
        reversed_number = reversed_number * 10 + remainder;
        
        // Remove the last digit from the original number
        n /= 10;
    }

    std::cout << "Reversed Number = " << reversed_number << std::endl;

    return 0;
}
