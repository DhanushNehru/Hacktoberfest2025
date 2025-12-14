#include <bits/stdc++.h>
using namespace std;

/*
    Problem: N-Queens II
    Difficulty: Hard
    Approach: Backtracking with safety checks for columns and diagonals
*/

class Solution {
private:
    int result = 0;
    vector<bool> col, diag1, diag2;

    // Recursive backtracking function
    void solve(int n, int row) {
        // Base case: if all queens are placed
        if (row == n) {
            result++;
            return;
        }

        for (int c = 0; c < n; c++) {
            // Check if the current position is safe
            if (!col[c] && !diag1[row + c] && !diag2[row - c + n - 1]) {
                // Place queen
                col[c] = diag1[row + c] = diag2[row - c + n - 1] = true;
                solve(n, row + 1); // Move to next row
                // Backtrack (remove queen)
                col[c] = diag1[row + c] = diag2[row - c + n - 1] = false;
            }
        }
    }

public:
    int totalNQueens(int n) {
        col.assign(n, false);
        diag1.assign(2 * n - 1, false);
        diag2.assign(2 * n - 1, false);
        solve(n, 0);
        return result;
    }
};

int main() {
    Solution obj;
    int n;
    cout << "Enter value of n: ";
    cin >> n;
    cout << "Total distinct solutions for " << n << "-Queens: " << obj.totalNQueens(n) << endl;
    return 0;
}

/*
    Time Complexity: O(N!)
    Space Complexity: O(N)
    Notes:
    - This is a classic backtracking problem.
    - Uses diagonal hashing to optimize conflict checking.
    - Great demonstration of recursion, constraint satisfaction, and optimization.
*/
