#include <iostream>
#include <vector>
using namespace std;

void generateSubsets(vector<int>& nums, int index, vector<int>& current, vector<vector<int>>& result) {
    if (index == nums.size()) {
        result.push_back(current); 
        return;
    }

    generateSubsets(nums, index + 1, current, result);

    current.push_back(nums[index]);
    generateSubsets(nums, index + 1, current, result);
    current.pop_back(); 
}

int main() {
    vector<int> nums = {1, 2, 3}; // Example input
    vector<vector<int>> result;
    vector<int> current;

    generateSubsets(nums, 0, current, result);

    cout << "All subsets:\n";
    for (const auto& subset : result) {
        cout << "{ ";
        for (int x : subset) cout << x << " ";
        cout << "}\n";
    }

    return 0;
}
