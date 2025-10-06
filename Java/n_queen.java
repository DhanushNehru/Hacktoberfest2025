import java.util.*;
import java.io.*;

public class n{
    public static void main(String[] args) throws Exception{
        Scanner scn = new Scanner(System.in);
        int n=scn.nextInt();
        int[][] chess = new int[n][n];
        printNQueens(chess,"",0);
    }
    public static void printNQueens(int[][] chess, String psf, int row){
        if(row == chess.length){
            System.out.println(psf + ".");
            return;
        }
        for(int col =0; col<chess.length;col++){
            if(isQueenSafee(chess,row,col)== true){
                chess [row][col]=1;
                printNQueens(chess, psf + row + "-" +col + ",", row+1);
                chess[row][col]=0;
            }
        }
    }
    public static boolean isQueenSafee(int[][] chess ,int row, int col){
        for(int i=row-1, j=col ; i>=0;i--){
            if(chess[i][j] == 1){
                return false;
            }
        }
        for(int i=row-1 , j=col-1 ; i>=0&& j>=0;i--,j--){
            if(chess[i][j] == 1){
                return false;
            }
        }
        for(int i=row-1 , j=col+1 ; i>=0 && j<chess.length;i--,j++){
            if(chess[i][j]==1){
                return false;
            }
        }
        return true;
    }
}