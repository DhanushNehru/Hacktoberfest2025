class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res , new ArrayList<>(), k , n , 1);
        return res;
    }
    private void backtrack(List<List<Integer>> res , List<Integer>ds , int k , int n , int start){
        if (n == 0 && ds.size() == k) {
            res.add(new ArrayList<>(ds));
            return;
        }
        for(int i = start ; i<= 9 ; i++){
            ds.add(i);
            backtrack (res , ds , k , n-i , i+1);
            ds.remove(ds.size()-1);
        }
    }
}
