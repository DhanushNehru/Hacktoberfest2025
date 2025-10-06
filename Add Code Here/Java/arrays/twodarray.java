
import java.util.Scanner;

public class twodarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // int arr[][]={ 
        // {1,2,3},
        // {4,5,6},
        // {7,8,9}};
        
        // initialize array
        int arr[][] = new int[3][3];
        int n = arr.length;
        int m=arr[0].length;
        
        // input array 
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j]= sc.nextInt();
            }
            System.out.println("");
        }
        
        // print matrix
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(arr[i][j] +" ");
            }
            System.out.println("");
        }
    }
}
