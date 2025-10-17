class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int a = 0;
        int b = arr.length - 1;

        while(b - a + 1 > k){
            if(Math.abs(x - arr[a]) > Math.abs(arr[b] - x)){
                a++;
            }else{
                b--;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = a ; i <= b ; i++){
            result.add(arr[i]);
        }
        return result;


    }
}
