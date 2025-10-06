import java.util.Scanner;

public class TowerOfHanoi {
    public static void solveTowerOfHanoi(int n, char source, char auxiliary, char destination) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        
        solveTowerOfHanoi(n - 1, source, destination, auxiliary);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        solveTowerOfHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of disks: ");
        int numberOfDisks = scanner.nextInt();
        
        System.out.println("Steps to solve Tower of Hanoi with " + numberOfDisks + " disks:");
        solveTowerOfHanoi(numberOfDisks, 'A', 'B', 'C');
        
        scanner.close();
    }
}
