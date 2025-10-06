class Solution {
    vector<string> ans;
    void solver(TreeNode* root,string s) {
        if(!root->left && !root->right) {ans.push_back(s);
                   return;
                  }
        if(root->left) solver(root->left,s+"->"+ to_string(root->left->val));
        if(root->right) solver(root->right,s+"->"+ to_string(root->right->val));
        
    }
public:
    vector<string> binaryTreePaths(TreeNode* root) {
         if(!root) return ans;
        
         solver(root,to_string(root->val));
        return ans;
    }
};
