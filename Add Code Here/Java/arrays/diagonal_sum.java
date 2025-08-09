public class diagonal_sum {

    public static int sum(int arr[][]){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(i==j){
                    sum += arr[i][j];
                }
                else if(i+j==arr.length-1){
                    sum += arr[i][j]; 
                }
            }
        }
        return sum;

    }
    public static void main(String[] args) {
        int arr[][] = {
            {1, 3, 4},
            {4, 5, 6},
            {8, 9, 7},
            {1, 3, 4}
        };
        System.out.println(sum(arr));
    }
}
