//Problem Statement: You are given an array of prices where prices[i] is the price of a given stock on an ith day.

//You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

/*Examples:-

Example 1:
Input:
 prices = [7,1,5,3,6,4]
Output:
 5
Explanation:
 Buy on day 2 (price = 1) and 
sell on day 5 (price = 6), profit = 6-1 = 5.

Note
: That buying on day 2 and selling on day 1 
is not allowed because you must buy before 
you sell.

Example 2:
Input:
 prices = [7,6,4,3,1]
Output:
 0
Explanation:
 In this case, no transactions are 
done and the max profit = 0. */


#include<bits/stdc++.h>
using namespace std;

We will solve this problem in 3 ways:
1️⃣ Brute Force Approach (O(n²))
2️⃣ Better Approach (O(n))
3️⃣ Optimal Approach (O(n))
*/

/* 🥉 1. Brute Force Approach - Try every possible pair
--------------------------------------------------------
- For each day, try buying on that day and selling on every later day.
- Track the maximum profit.

Time Complexity: O(n²)
Space Complexity: O(1)
*/
int maxProfitBrute(vector<int> &prices) {
    int n = prices.size();
    int maxProfit = 0;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            maxProfit = max(maxProfit, prices[j] - prices[i]);
        }
    }
    return maxProfit;
}

/* 🥈 2. Better Approach - Track future max price
--------------------------------------------------------
- Traverse from right to left.
- At each index, calculate profit if we buy today and sell at the future max price.
- Keep track of the max profit.

Time Complexity: O(n)
Space Complexity: O(1)
*/
int maxProfitBetter(vector<int> &prices) {
    int n = prices.size();
    int maxProfit = 0;
    int maxFuturePrice = 0; // max price seen from the right side

    for (int i = n - 1; i >= 0; i--) {
        maxFuturePrice = max(maxFuturePrice, prices[i]);
        maxProfit = max(maxProfit, maxFuturePrice - prices[i]);
    }
    return maxProfit;
}

/* 🥇 3. Optimal Approach - Track minimum price so far
--------------------------------------------------------
- Traverse from left to right.
- Keep track of the minimum price seen so far.
- At each step, calculate profit if sold today.
- Keep track of the maximum profit.

Time Complexity: O(n)
Space Complexity: O(1)
*/
int maxProfitOptimal(vector<int> &prices) {
    int minPrice = INT_MAX;
    int maxProfit = 0;

    for (int price : prices) {
        minPrice = min(minPrice, price);               // Best day to buy so far
        maxProfit = max(maxProfit, price - minPrice);  // Best profit if sold today
    }
    return maxProfit;
}

int main() {
    int n;
    cout << "Enter number of days: ";
    cin >> n;

    vector<int> prices(n);
    cout << "Enter stock prices: ";
    for (int i = 0; i < n; i++) cin >> prices[i];

    cout << "\n📊 Results:\n";
    cout << "1️⃣ Brute Force Profit: " << maxProfitBrute(prices) << endl;
    cout << "2️⃣ Better Approach Profit: " << maxProfitBetter(prices) << endl;
    cout << "3️⃣ Optimal Approach Profit: " << maxProfitOptimal(prices) << endl;

    return 0;
}

/*
✅ Summary:
- Brute Force: O(n²) — Slow for large inputs.
- Better:      O(n)  — Single pass from right to left.
- Optimal:     O(n)  — Most efficient, single pass from left to right.

🎯 Always explain all three approaches in interviews to show optimization thinking!
*/