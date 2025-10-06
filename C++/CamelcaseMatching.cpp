#include <iostream>
#include <vector>
#include <string>
using namespace std;

bool isMatch(const string& query, const string& pattern) {
    int j = 0;
    for (int i = 0; i < query.size(); ++i) {
        if (j < pattern.size() && query[i] == pattern[j]) {
            j++;
        } else if (isupper(query[i])) {
            return false;
        }
    }
    return j == pattern.size();
}

vector<bool> camelMatch(vector<string>& queries, string pattern) {
    vector<bool> results;
    for (const string& query : queries) {
        results.push_back(isMatch(query, pattern));
    }
    return results;
}

int main() {
    vector<string> queries = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
    string pattern = "FB";
    vector<bool> results = camelMatch(queries, pattern);
    for (bool result : results) {
        cout << (result ? "true" : "false") << endl;
    }
    return 0;
}
