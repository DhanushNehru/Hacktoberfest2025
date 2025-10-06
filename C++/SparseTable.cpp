#include<iostream>
#include<vector>
#include<numeric>

using namespace std;

// Sparse Table class for efficient Range Minimum and Maximum Queries
class sparseTable
{
private:
    // Precomputed logarithms for fast queries
    vector<int> lg;                 
    // Sparse table for range minimum queries
    vector<vector<int>> mini;       
    // Sparse table for range maximum queries
    vector<vector<int>> maxi;       
    // n: size of input, levels: max power of 2 <= n
    int n, levels;                  

public:
    // Constructor: Builds the sparse tables for min and max in O(n log n)
    sparseTable(const vector<int> &nums)
    {
        n = nums.size();
        if (n == 0)
        {
            levels = 0;
            return;
        }

        // Calculate the number of levels needed (log2(n) + 1)
        levels = 32 - __builtin_clz(n); 

        // Precompute logarithms for fast query calculation
        lg.assign(n + 1, 0);
        for (int i = 2; i <= n; ++i)
            lg[i] = lg[i / 2] + 1;

        // Initialize sparse tables
        mini.assign(n, vector<int>(levels));
        maxi.assign(n, vector<int>(levels));

        // Base case: intervals of length 1
        for (int i = 0; i < n; ++i)
        {
            mini[i][0] = nums[i];
            maxi[i][0] = nums[i];
        }

        // Build the tables for intervals of length 2^j
        for (int j = 1; j < levels; ++j)
        {
            int half = 1 << (j - 1); // Length of the first half
            for (int i = 0; i + (1 << j) <= n; ++i)
            {
                // For min: take the minimum of two overlapping intervals
                mini[i][j] = min(mini[i][j - 1], mini[i + half][j - 1]);
                // For max: take the maximum of two overlapping intervals
                maxi[i][j] = max(maxi[i][j - 1], maxi[i + half][j - 1]);
            }
        }
    }

    // Query for minimum in range [l, r] in O(1)
    int rangeMin(int l, int r)
    {
        int len = r - l + 1;
        // Largest power of 2 that fits in the range
        int j = lg[len]; 
        // Answer is the min of two intervals covering [l, r]
        return min(mini[l][j], mini[r - (1 << j) + 1][j]);
    }

    // Query for maximum in range [l, r] in O(1)
    int rangeMax(int l, int r) const
    {
        int len = r - l + 1;
        int j = lg[len];
        return max(maxi[l][j], maxi[r - (1 << j) + 1][j]);
    }
};

int main(){
    int n = 10;
    vector<int> vec(n);
    iota(vec.begin(), vec.end(), 1); // Fill vec with 1,2,...,10

    // Build the sparse table
    sparseTable st(vec);

    // Demonstrate queries
    // Should print 3
    cout << "Range Min [2, 5]: " << st.rangeMin(2, 5) << endl; 
    // Should print 6
    cout << "Range Max [2, 5]: " << st.rangeMax(2, 5) << endl; 
    // Should print 1
    cout << "Range Min [0, 9]: " << st.rangeMin(0, 9) << endl; 
    // Should print 10
    cout << "Range Max [0, 9]: " << st.rangeMax(0, 9) << endl; 

    return 0;
}