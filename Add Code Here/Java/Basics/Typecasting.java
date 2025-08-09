import java.util.Scanner;

public class Typecasting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float a = 25.10f;
        int b=(int)a;
        System.out.print("Float value "+a+" is type cast into integer:");
        System.out.println(b);
        
        char ch1 = 'a';
        char ch2 = 'b';
        int num1 = ch1;
        int num2 = ch2;
        System.out.print("The value of a&b coverted to the Integer: ");
        System.out.print(num1+ " ");
        System.out.println(num2+ " ");
        

    }
}
