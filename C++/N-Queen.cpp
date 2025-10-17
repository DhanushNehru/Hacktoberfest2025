#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> res;
        vector<string> board(n, string(n, '.')); 
        vector<int> col(n, 0), diag1(2 * n, 0), diag2(2 * n, 0);

        backtrack(0, n, board, res, col, diag1, diag2);
        return res;
    }

private:
    void backtrack(int row, int n, vector<string> &board,
                   vector<vector<string>> &res,
                   vector<int> &col, vector<int> &diag1, vector<int> &diag2) {
        if (row == n) {
            res.push_back(board);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (col[c] || diag1[row + c] || diag2[row - c + n]) continue;

        
            board[row][c] = 'Q';
            col[c] = diag1[row + c] = diag2[row - c + n] = 1;

            
            backtrack(row + 1, n, board, res, col, diag1, diag2);

            
            board[row][c] = '.';
            col[c] = diag1[row + c] = diag2[row - c + n] = 0;
        }
    }
};
