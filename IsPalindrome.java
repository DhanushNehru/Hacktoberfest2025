import java.util.Scanner;
public class IsPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine().toLowerCase();
        int c=str.length()-1;
        boolean isPalindrome =true;

        for(int i=0;i<str.length()/2;i++){
            if(str.charAt(i)!=str.charAt(c--)){
                isPalindrome=false;
            }
        }
        System.out.println("Is the Entered string palindrome: "+isPalindrome);
    }
}
