
import java.util.Scanner;



public class evenodd{

    public static boolean  even(int a){
        if (a % 2==0){
            return true;
        }
        else{
            return false;
        }  }      
        

    public static void main(String[] args)
    {   Scanner sc =new Scanner(System.in);
        System.out.print("Enter a Number: ");
        int a= sc.nextInt() ;      
        boolean ans = even(a);
        System.out.println(ans);

    }
}