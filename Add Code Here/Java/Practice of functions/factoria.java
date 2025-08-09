package Functions;
import java.util.Scanner;

public class factoria{
    public static int factoria(int a){
        int f=1;
        for (int i = 1; i<=a ;i++) {
        f = f*i;
        }
        return f;


    }

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int a= sc.nextInt();
            int fact=factoria(a);
            System.out.println("factorial:"+fact);
    }
}
