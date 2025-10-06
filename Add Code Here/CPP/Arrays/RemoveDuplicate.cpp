class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
       auto it =  unique(nums.begin(), nums.end());
         return distance(nums.begin(),it);
    }
};
