
import java.util.*;

public class ArithmeticOperation{
      public static void main(String[] args) {
              Scanner sc = new Scanner(System.in);
              System.out.print("Enter a first element:");
              int a = sc.nextInt();
              System.out.print("Enter a first element:");
              int b = sc.nextInt();
              int diff = a-b;
              int sum = a+b;
              int multi = a*b;
              System.out.println("Addition of " + a + " and " + b + " is: " + sum);
              System.out.println("Subtraction of " + a + " and " + b + " is: " + diff);
              System.out.println("Multiplication of " + a + " and " + b + " is: " + multi);
            String name = sc.nextLine();
            System.out.println(name);

          }
    }