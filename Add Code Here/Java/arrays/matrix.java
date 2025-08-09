
import java.util.*;

public class matrix{
    public static void main(String[]arrgs){
        int matrix[][]= new int[3][3];
        int n = matrix.length;
        int m = matrix[0].length;
        Scanner sc = new Scanner(System.in);

        
        System.out.println("Enter the matrix element: ");
        for(int i=0;i<n;i++){
            for(int j=0; j<m;j++){
                matrix[i][j]= sc.nextInt();
            }
        }

        System.out.println("Your matrix of elements:");
        for(int i=0;i<n;i++){
            for(int j=0; j<m;j++){
                System.out.print(matrix[i][j] + " ");
        }    
        System.out.println("");  
        }
    }}
