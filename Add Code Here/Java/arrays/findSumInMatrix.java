public class findSumInMatrix {

    public static void sum(int[][] arr){
        
        // approach 1
        int n=arr.length;
        int m= arr[0].length;
        int sum=0;

        //approach 1
        if(n<=1){
            System.out.print("Array is one dimentional.");
        }
        else{for(int j=0;j<m;j++){
            sum+=arr[1][j];
        }
        System.out.println("The sum of the second row : "+sum);}

        // for(int i=0;i<n;i++){
        //     for(int j=0;j<m;j++){
        //         if(i==1){
        //             sum+=arr[1][j];
        //         }
        //     }
        // }
        
    }
    public static void main(String[] args){
        // int[][] arr={ 
        // {1,2,3},
        // {4,5,2},
        // {7,8,9}};
        int[][] arr = {
            {1,6,8,6}
        };
        sum(arr);
    }
}
