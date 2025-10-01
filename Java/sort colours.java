//basic driver code for test cases
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums1);
        System.out.println("Sorted colors for nums1: " + Arrays.toString(nums1)); 
        // Expected output: [0, 0, 1, 1, 2, 2]

        // Test case 2
        int[] nums2 = {2, 0, 1};
        solution.sortColors(nums2);
        System.out.println("Sorted colors for nums2: " + Arrays.toString(nums2)); 
        // Expected output: [0, 1, 2]

        // Test case 3
        int[] nums3 = {0};
        solution.sortColors(nums3);
        System.out.println("Sorted colors for nums3: " + Arrays.toString(nums3)); 
        // Expected output: [0]

        // Test case 4
        int[] nums4 = {1, 2, 0, 1, 2, 1, 0};
        solution.sortColors(nums4);
        System.out.println("Sorted colors for nums4: " + Arrays.toString(nums4)); 
        // Expected output: [0, 0, 1, 1, 1, 2, 2]

        // Test case 5
        int[] nums5 = {2, 2, 


//main code using DNF approach
class Solution {
    public void sortColors(int[] nums) {
        int mid=0, low=0, high=nums.length-1;

        while(mid <= high){
            if (nums[mid]==2){
                int temp=nums[mid];
                nums[mid]=nums[high];
                nums[high]=temp;

                high--;
            }

            else if(nums[mid]==0){
                int temp=nums[mid];
                nums[mid]=nums[low];
                nums[low]=temp;

                low++; mid++;
            }

            else{
                mid++;
            }
        }
    }
}
