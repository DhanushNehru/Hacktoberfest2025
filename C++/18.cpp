#include <iostream>
// 5 4 2 7 11 10 8 13 17 16 14 19 23 22 20 25 29 28 26 31 35 34 32 37 41 4 0 38 43 47 46 44 49
// 2 4 5 7 8  10 11 13 14 17 .....
using namespace std;
int main()
{
    int i = 1;
    while (true) {
        i++;
        if (i % 3 == 0) {
            continue;
        } else if (i == 50) {
            break;
        }
        if (i % 2 == 0) {
            i += 3;
        } else {
            i -= 3;
        }
        cout << i << " ";
    }

    return 0;
}