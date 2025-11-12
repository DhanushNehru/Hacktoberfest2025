#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int totalNQueens(int n) {
        vector<bool> cols(n, false), diag1(2 * n, false), diag2(2 * n, false);
        int count = 0;
        backtrack(0, n, cols, diag1, diag2, count);
        return count;
    }

private:
    void backtrack(int row, int n, vector<bool>& cols, vector<bool>& diag1, vector<bool>& diag2, int& count) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (cols[col] || diag1[row + col] || diag2[row - col + n])
                continue;
            cols[col] = diag1[row + col] = diag2[row - col + n] = true;
            backtrack(row + 1, n, cols, diag1, diag2, count);
            cols[col] = diag1[row + col] = diag2[row - col + n] = false;
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    Solution sol;
    vector<int> tests = {1, 4, 8};
    for (int n : tests) {
        cout << "For N = " << n << ", Number of Solutions = " << sol.totalNQueens(n) << "\n";
    }
    return 0;
}
