#include <iostream>
#include <string>
using namespace std;


#define d 256 


void rabinKarp(const string& text, const string& pattern, int q) {
    int n = text.size();
    int m = pattern.size();
    int i, j;
    int p = 0;
    int t = 0;
    int h = 1;

 
    for (i = 0; i < m - 1; i++) {
        h = (h * d) % q;
    }

    for (i = 0; i < m; i++) {
        p = (d * p + pattern[i]) % q;
        t = (d * t + text[i]) % q;
    }

  
    for (i = 0; i <= n - m; i++) {
       
        if (p == t) {

            for (j = 0; j < m; j++) {
                if (text[i + j] != pattern[j]) {
                    break;
                }
            }
            if (j == m) {
                cout << "Pattern found at index " << i << endl;
            }
        }

       
        if (i < n - m) {
            t = (d * (t - text[i] * h) + text[i + m]) % q;
            if (t < 0) {
                t = (t + q);
            }


int createHash(const string& str, int end, int prime) {
    int hashValue = 0;
    for (int i = 0; i <= end; i++) {
        hashValue += str[i] * pow(prime, i);
    }
    return hashValue;
}


int recalculateHash(const string& str, int oldIndex, int newIndex, int oldHash, int patternLength, int prime) {
    int newHash = oldHash - str[oldIndex];
    newHash /= prime;
    newHash += str[newIndex] * pow(prime, patternLength - 1);
    return newHash;
}


bool checkEqual(const string& text, const string& pattern, int start) {
    for (int i = 0; i < pattern.length(); i++) {
        if (text[start + i] != pattern[i]) {
            return false;
        }
    }
    return true;
}

void rabinKarp(const string& text, const string& pattern) {
    int prime = 101; 
    int patternLength = pattern.length();
    int textLength = text.length();

    int patternHash = createHash(pattern, patternLength - 1, prime);
    int textHash = createHash(text, patternLength - 1, prime);

    for (int i = 0; i <= textLength - patternLength; i++) {
      
        if (patternHash == textHash && checkEqual(text, pattern, i)) {
            cout << "Pattern found at index: " << i << endl;
        }
        
        if (i < textLength - patternLength) {
            textHash = recalculateHash(text, i, i + patternLength, textHash, patternLength, prime);

        }
    }
}

int main() {

    string text, pattern;
    int q = 101; 

 
    cout << "Enter the text: ";
    getline(cin, text);

    cout << "Enter the pattern to search: ";
    getline(cin, pattern);

  
    rabinKarp(text, pattern, q);

    string text = "ababcabcabababd";
    string pattern = "ababd";

    rabinKarp(text, pattern);


    return 0;
}



// Time complexity: O(n + m) where n is the length of the text and m is the length of the pattern

// Space complexity: O(1)

// Sample Input: Enter the text: abcabcabcabc
// Enter the pattern to search: abc

// Sample Output: Pattern found at index 0
// Pattern found at index 3
// Pattern found at index 6
// Pattern found at index 9


// Sample Input
// Text:    "ababcabcabababd"
// Pattern: "ababd"


// Sample Output
// Pattern found at index: 10

