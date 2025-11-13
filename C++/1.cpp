#include <iostream>
#include <math.h>

using namespace std;

float Vol(int r)
{
    return (4 / 3.0) * M_PI * (pow(r, 3));
}

int main()
{
    int r;
    cout << "Enter the Radius of Sphere: ";
    cin >> r;
    cout << "The Volume of Sphere is : " << Vol(r);
    return 0;
}