
import java.util.Scanner;

public class searchInSortedMatrix {

    /**
     * @param arr
     * @param key
     * @return
     */
    public static boolean  search(int arr[][] , int key){
        int row=0;
        int col= arr[0].length-1;
        while( row < arr.length && col>=0){
            if(arr[row][col]==key){
                System.out.print("Found key at:("+row+","+col+")");
                return true;
        }
        else if(key <arr[row][col] ){
            col--;
        }
        else{
            row++;
        }
    }
        return false;
}
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int arr[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}
        };
        System.out.print("Enter key: ");
        int key = sc.nextInt();
        search(arr, key);
    }
}
