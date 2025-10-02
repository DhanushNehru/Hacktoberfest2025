import java.util.ArrayList;
import java.util.List;

public class ArrayPermutation2 {// Boolean array method............
    public static List<List<Integer>> Giveperm(int[] nums, List<Integer> arr, boolean[] Isvisited,
            List<List<Integer>> ans) {
        if (arr.size() == nums.length) {
            ArrayList<Integer> ds = new ArrayList<>();// creating new arraylist again because if we add the arr
                                                      // arraylist in the answer so it show the error because the arr is
                                                      // passed by reference so creating new arraylist and copying the
                                                      // element of arr into ds and adding it into the answer
                                                      // ...........
            for (var i : arr) {
                ds.add(i);
            }
            ans.add(ds);// adding the current arraylist into the answer..........
            return ans;// returning the answer i.e listoflist.............
        }
        for (int i = 0; i < nums.length; i++) {
            if (Isvisited[i] == false) {// if current Isvisited is not true means the ith index of the array is not
                                        // visited........
                arr.add(nums[i]);// adding the each element saparately.......means choosing ith element at a
                                 // time.........
                Isvisited[i] = true;// marking the boolean array as visited..........
                Giveperm(nums, arr, Isvisited, ans);// Recursion work....
                Isvisited[i] = false;// backtracking ........unmarking the boolean array as well as removing the
                arr.remove(arr.size() - 1); // element from the arraylist.....
                // choosing last idx because we assume that element is in last index of the
                // arraylist......
            }
        }
        return ans;
    }

    public static List<List<Integer>> permute(int[] nums) {// Helper function............
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        boolean[] Isvisited = new boolean[nums.length];
        return Giveperm(nums, arr, Isvisited, ans);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> ans = permute(nums);
        System.out.println(ans);
    }
}
