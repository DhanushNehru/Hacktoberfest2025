import java.util.Scanner;

public class invertedstar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of lines you want to print: ");
        int a = sc.nextInt();

        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= (a - i + 1); j++) {
                System.out.print("*");
            }
            System.out.println();  // Move to the next line after printing stars in the current row
        }

        sc.close();  // Close the scanner
    }
}
