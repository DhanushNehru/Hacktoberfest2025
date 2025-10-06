#include <iostream>
using namespace std;
char getMax(string s)
{
    int arr[26] = {0};
    // Create an array of count of characters
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        // lowercase
        int number = 0;
        number = ch - 'a';
        arr[number]++;
    }
    int maxi = -1, ans = 0;
    for (int i = 0; i < 26; i++)
    {
        if (maxi < arr[i])
        {
            ans = i;
            maxi = arr[i];
        }
    }
    char finalAns = 'a' + ans;
    return finalAns;
}
string replaceSpaces(string s)
{
    string temp = "";
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == ' ')
        {
            temp.push_back('@');
            temp.push_back('4');
            temp.push_back('0');
        }
        else
        {
            temp.push_back(s[i]);
        }
    }
    return temp;
}
int main()
{
    char s[50];
    // cin >> s;
    // cout << getMax(s) << endl;
    cout << "Enter the string " << endl;
    cin.getline(s, 50);
    cout << "String without spaces is " << replaceSpaces(s);
    return 0;
}