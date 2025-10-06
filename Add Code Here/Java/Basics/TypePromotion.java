
import java.util.Scanner;

public class TypePromotion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char a = 'a';
        char b = 'b';
        System.out.println((int)a);
        System.out.println((int)b);
        
        byte d = 5;
        d=(byte)(d);
        System.out.println(d);
    }
}
