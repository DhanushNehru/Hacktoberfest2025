class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int maxright = prices[n-1];
        int maxprof = 0;

        for(int i=n-2 ; i>=0 ; i--){
            maxright = max(prices[i] , maxright);
            maxprof = max(maxprof , maxright-prices[i]);
        }
        return maxprof;
    }
};
