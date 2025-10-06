public class X_pattern{

    public static void main(String[] args) {
        int n=5;
        for (int i = 1; i <= n; i++) {          //number of rows
            for (int j = 1; j <= n; j++) // number of columns
            {       
                if(i-j==0 || j+i==n+1){ 
                    System.out.print("*"); //same line
            }else{
                System.out.print(" ");
            }
        }
            System.out.println();               // Move to the next line after printing one row
        }
    }
    }

