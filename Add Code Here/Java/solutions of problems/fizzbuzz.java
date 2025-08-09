
import java.util.Scanner;
public class fizzbuzz{
    public static void main(String[]args){
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter your NO: ");
            int a = sc.nextInt();
        if (a%3 == 0 && a%5 !=0){
            System.out.print("Fizz");
        }
        else if (a%5 == 0 && a%3 !=0){
            System.out.print("Buzz");
        }
            
        else if (a%5 == 0 && a%3 ==0){
            System.out.print("Fizz-Buzz");
        }
        else{
            System.out.print("No itself");
        }
    }
}
