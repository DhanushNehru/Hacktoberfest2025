//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

class Solution {
  public:
      int lengthOfLongestSubstring(string s) {
          int ans = 0,start = 0,n = s.size();
          unordered_map<char,int> hash;
          for(int i = 0;i<n;i++){
              start = max(hash[s[i]],start);
              ans = max(ans,(i - start) + 1);
              hash[s[i]] = i + 1;
          }
          return ans;
      }
  };