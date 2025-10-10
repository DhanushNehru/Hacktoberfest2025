class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size(); int m = nums2.size();
        int i = 0; int j = 0;

        vector<double> ans;

        while(i < n && j < m){
            if(nums1[i] <= nums2[j]){
                ans.push_back(nums1[i]);
                i++;
            }else{
                ans.push_back(nums2[j]);
                j++;
            }
        }

        while(j < m){
            ans.push_back(nums2[j]);
            j++;
        }
        while(i < n){
            ans.push_back(nums1[i]);
            i++;
        }

        if(ans.size() % 2 == 0){    // If size is even
            int s = 0; int e = ans.size() - 1;
            int mid = s + (e-s)/2; 
            int mid1 = mid + 1;

            double res = (ans[mid] + ans[mid1])/2;
            return res;

        }else{
            int s = 0; int e = ans.size() - 1;
            int mid = s + (e-s)/2;
            return ans[mid];
        }

        return -1;
    }
};
