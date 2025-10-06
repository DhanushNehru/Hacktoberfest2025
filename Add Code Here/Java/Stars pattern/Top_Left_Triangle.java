
public class Top_Left_Triangle{
    public static void main(String[] args) {
        int n=5;
        for (int i = 1; i <= n; i++) {          //number of rows
            for (int j = 1; j <= n; j++) // number of columns
            {       
                if(i+j<=n+1){ 
                    System.out.print("*"); //same line
            }
        }
            System.out.println();               // Move to the next line after printing one row
        }
    }
}

