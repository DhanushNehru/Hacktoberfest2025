class Solution {
public:
    int largestPerimeter(vector<int>& nums) {
        if(nums.size() < 3)
        {
            return 0;
        }
        
        int ans = 0;
        // check valid triangle 
        sort(nums.begin() , nums.end());
        for(int i = nums.size()-3 ; i >= 0 ; --i)
        {
            if(nums[i] + nums[i+1] > nums[i+2])
            {
                return nums[i] + nums[i+1] + nums[i+2];
            }
        }



        return 0;

        //find largest or not 
    }
};
