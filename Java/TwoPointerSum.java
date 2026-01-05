import java.util.Scanner;

public class TwoPointerSum {

    public static void findPairWithSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        boolean found = false;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                System.out.println("Pair found: " + arr[left] + " + " + arr[right] + " = " + target);
                found = true;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        if (!found) {
            System.out.println("No pair found with sum " + target);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the sorted array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements in sorted order:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

        findPairWithSum(arr, target);

        scanner.close();
    }
}
