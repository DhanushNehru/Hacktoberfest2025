 class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMaxPath(root);
        return maxSum;
    }

    private int findMaxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftMax = Math.max(0, findMaxPath(root.left));
        int rightMax = Math.max(0, findMaxPath(root.right));

        // Calculate the max path sum through the current root
        int currentMax = leftMax + rightMax + root.val;
        maxSum = Math.max(maxSum, currentMax);

        // Return the max path sum of either left or right path including the root
        return Math.max(leftMax, rightMax) + root.val;
    }
}

