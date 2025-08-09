

public class top_right_triangle {
    public static void main(String[]args){
    
    int n=5;
    for (int i = 1; i <= n; i++) {          //number of rows
        for (int j = 1; j <= n; j++) // number of columns
        {       
            if(j-i>=0){ 
                System.out.print("*"); //same line
        }else{
            System.out.print(" ");
        }
    }
        System.out.println();               // Move to the next line after printing one row
    }
}
}
