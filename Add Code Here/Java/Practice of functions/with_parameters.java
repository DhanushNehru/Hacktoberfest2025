package Functions;


import java.util.Scanner;

public class with_parameters {

    

// functions with parameters 
    public static int sum(int a,int b){
            int sum = a+b;
            return sum;
        }

//MAIN FUNCTION]
public static void main(String[]args){
            Scanner sc =new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            int sum=sum(a, b);
            System.out.println("sum:"+sum);
}

}
