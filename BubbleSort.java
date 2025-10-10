class Solution {
    public void bubbleSort(int[] arr) {
        // code here
        for(int i=0;i<arr.length-1;i++){// NUMBER OF PASSES
            for (int j=0;j<arr.length-1-i;j++){ // number of swaps
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
