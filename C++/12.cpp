#include <iostream>
#include <math.h>
// SCAlene Triangle
//  A =1/2 * ab * sinC /
using namespace std;
float Area(int s1, int s2, float a)
{
    return 0.5 * s1 * s2 * sin((M_PI / 180) * a);
}
int main()
{
    float a;
    int s1, s2;
    cout << "Enter the one side of triangle: ";
    cin >> s1;
    cout << "Enter the another side of triangle: ";
    cin >> s2;
    cout << "Enter the Angle between these sides: ";
    cin >> a;

    cout << "The Area of Triangle by Scalene triangle: " << Area(s1, s2, a);
    return 0;
}