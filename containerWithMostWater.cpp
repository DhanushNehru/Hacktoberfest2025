class Solution {
public:
    int maxArea(vector<int>& height) {
        int left=0;
        int right=height.size()-1;
        int maxi=INT_MIN;
        while(left<right){
            int x1=left;
            int y1=height[left];
            int x2=right;
            int y2=height[right];
            int area=(right-left)*min(y1,y2);
            maxi=max(maxi,area);
            if(height[left]<height[right])left++;
            else right--;

        }
        return maxi;
    }
};
