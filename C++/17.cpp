#include <cmath>
#include <ctime>
#include <iostream>
using namespace std;
int main()
{
    time_t t = time(NULL);
    tm* tptr = localtime(&t);
    cout << tptr->tm_sec << endl;
    cout << tptr->tm_mday << endl;
    cout << tptr->tm_year << endl;
    cout << "Date is: " << tptr->tm_hour << ":" << tptr->tm_min << ":" << tptr->tm_sec << endl;
    cout << "Date is: " << tptr->tm_mday << ":" << tptr->tm_mon << ":" << tptr->tm_year + 1900 << endl;
    return 0;
}