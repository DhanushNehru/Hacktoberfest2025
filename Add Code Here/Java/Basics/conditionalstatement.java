
import java.util.Scanner;

public class conditionalstatement {
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
    //System.out.print("enter your age:");
    //   int a = sc.nextInt();
    //   if(a>=18){
    //     System.out.println("you can vote and drive");
    //   }
    //   else{
    //     System.out.println("you can not vote and drive");
    //   }
    System.out.print("Enter A:");
    int a=sc.nextInt();
    System.out.print("Enter b:");
    int b=sc.nextInt();
if (a>b){
    System.out.println("a is big");
}
else{
    System.out.println("b is greater");
}
    }
}
