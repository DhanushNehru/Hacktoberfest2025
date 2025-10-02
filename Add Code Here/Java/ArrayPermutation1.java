import java.util.ArrayList;
import java.util.List;

// taking an extra index and swaping each idx of array with extra index method.................
public class ArrayPermutation1 {// this method requre less space...............time complexity is aprox
                                // O(n!)..........
    public static List<List<Integer>> Giveperm(int[] nums, int idx, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ArrayList<Integer> ds = new ArrayList<>();// creating new arraylist............
            for (var i : nums) {// copying the swaped element in the arraylist........
                ds.add(i);
            }
            ans.add(ds);// adding the current arraylist into the answer..........
            return ans;// returning the answer i.e listoflist.............
        }
        for (int i = 0; i < nums.length; i++) {
            if (i >= idx) {// if the current index is not less than the extra index.......because we will
                           // not swap the any idx-i index of the array....
                int temp = nums[idx];// swaping the values......
                nums[idx] = nums[i];
                nums[i] = temp;
                Giveperm(nums, idx + 1, ans);// Recursion work....giving next idx....
                int tempo = nums[idx];// backtracking ...again swaping the values...
                nums[idx] = nums[i];
                nums[i] = tempo;
            }
        }
        return ans;
    }

    public static List<List<Integer>> permute(int[] nums) {// Helper function............
        List<List<Integer>> ans = new ArrayList<>();
        return Giveperm(nums, 0, ans);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> ans = permute(nums);
        System.out.println(ans);
    }
}