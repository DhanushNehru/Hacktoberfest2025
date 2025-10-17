#include <iostream>
using namespace std;

// Function to solve 0/1 Knapsack Problem using Dynamic Programming
int knapsack(int W, int wt[], int val[], int n) {
    int dp[n + 1][W + 1];

    // Build table dp[][] in bottom-up manner
    for (int i = 0; i <= n; i++) {
        for (int w = 0; w <= W; w++) {
            if (i == 0 || w == 0)
                dp[i][w] = 0;
            else if (wt[i - 1] <= w)
                dp[i][w] = max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
            else
                dp[i][w] = dp[i - 1][w];
        }
    }

    return dp[n][W];  // Maximum value possible
}

int main() {
    int n; // Number of items
    cout << "Enter number of items: ";
    cin >> n;

    int val[n], wt[n];
    cout << "Enter values of items:\n";
    for (int i = 0; i < n; i++) cin >> val[i];

    cout << "Enter weights of items:\n";
    for (int i = 0; i < n; i++) cin >> wt[i];

    int W; // Maximum capacity of the knapsack
    cout << "Enter capacity of knapsack: ";
    cin >> W;

    cout << "Maximum value in knapsack = " << knapsack(W, wt, val, n) << endl;

    return 0;
}
