#include <iostream>
using namespace std;

// This is the function we want to maximize
double f(double x) {
    return -(x - 3) * (x - 3) + 9;
}

// Ternary Search function
double ternarySearch(double left, double right) {
    // We define a small value to stop the loop when the range is very small
    double epsilon = 0.000001;

    while ((right - left) > epsilon) {
        // Divide the range into three equal parts
        double mid1 = left + (right - left) / 3;
        double mid2 = right - (right - left) / 3;

        // Compare function values at mid1 and mid2
        if (f(mid1) < f(mid2)) {
            // Maximum lies between mid1 and right
            left = mid1;
        } else {
            // Maximum lies between left and mid2
            right = mid2;
        }
    }

    // Return the approximate maximum point
    return (left + right) / 2;
}

int main() {
    // Define the search range
    double left = 0;
    double right = 6;

    // Call ternary search
    double maxX = ternarySearch(left, right);
    double maxValue = f(maxX);

    // Print the result
    cout << "Maximum value of f(x) is " << maxValue << " at x = " << maxX << endl;

    return 0;
}
