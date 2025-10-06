#include <vector>
#include <algorithm>
#include <utility>

class Solution {
public:
    static bool cmp(const std::pair<int, int>& a, const std::pair<int, int>& b) {
        return a.second < b.second;
    }

    std::vector<int> arrayRankTransform(std::vector<int>& arr) {
        if (arr.empty()) {
            return {};
        }

        std::vector<std::pair<int, int>> num;
        for (int i = 0; i < arr.size(); i++) {
            num.push_back(std::make_pair(i, arr[i]));
        }

        std::sort(num.begin(), num.end(), cmp);

        int rank = 1;
        arr[num[0].first] = rank;

        for (int i = 1; i < num.size(); i++) {
            if (num[i].second != num[i - 1].second) {
                rank++;
            }
            arr[num[i].first] = rank;
        }

        return arr;
    }
};
