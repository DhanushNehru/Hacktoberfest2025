import java.util.Scanner;

public class Palindrom {
    static boolean Ispalindrom(String str, int left, int right) {
        if (left == right) {
            return true;
        }
        boolean a = (str.charAt(left) == str.charAt(right) && Ispalindrom(str, left + 1, right - 1));
        return a;
    }

    // returns true or false as palindrome or not........................
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int i = 0;
        int n = str.length() - 1;
        System.out.println(Ispalindrom(str, i, n));
    }
}