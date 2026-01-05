//Given a set of coin denominations and a target sum,
//find the number of ways to make change for the target sum or 
//the minimum number of coins required.

#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

int coinChange(vector<int>& coins, int amount) {
    // dp[i] = minimum coins needed to make amount i
    vector<int> dp(amount + 1, INT_MAX);
    dp[0] = 0; // Base case
    
    // For each amount from 1 to target amount
    for (int i = 1; i <= amount; i++) {
        // Try each coin
        for (int coin : coins) {
            if (coin <= i && dp[i - coin] != INT_MAX) {
                dp[i] = min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    
    return dp[amount] == INT_MAX ? -1 : dp[amount];
}

int main() {
    vector<int> coins = {1, 3, 4};
    int amount = 6;
    
    int result = coinChange(coins, amount);
    
    if (result == -1) {
        cout << "Cannot make amount " << amount << endl;
    } else {
        cout << "Minimum coins needed for amount " << amount << ": " << result << endl;
    }
    
    return 0;
}
