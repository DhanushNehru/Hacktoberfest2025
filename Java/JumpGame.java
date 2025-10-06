public class JumpGame {
    public boolean canJump(int[] nums) {
        int x = 0; 
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > x) {
                return false;
            }
            x = i + nums[i];
            if (x >= n - 1) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(jumpGame.canJump(nums1)); 
        System.out.println(jumpGame.canJump(nums2)); 
    }
}
