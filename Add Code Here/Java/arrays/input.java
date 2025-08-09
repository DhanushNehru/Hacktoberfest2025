import java.util.Scanner;

public class input {

    public static void update(int marks[]) {
        for (int i = 0; i < marks.length; i++) { // Changed <= to <
            marks[i] = marks[i] + 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        int marks[] = new int[n];

        // update(marks); // Uncomment this if you want to call the update function
        
        // for(int i=0;i<=marks.length;i++){ // Loop through the array to print elements
        //     System.out.print(marks[i]+""); // Print each element
        // }
        // System.out.println(""); // Move to the next line

        // taking n inputs
        System.out.print("Enter your marks:");
        for(int i = 0; i < n; i++) { // Loop to take input
            marks[i] = sc.nextInt(); // Using sc.nextInt() instead of nextInt()
        }

        // Printing the array
        System.out.print(" your marks are: ");
        for (int i = 0; i < marks.length; i++) { // Loop through the array to print elements
            System.out.print(marks[i] + " "); // Print each element
        }
        System.out.println(""); // Move to the next line
    }
}
