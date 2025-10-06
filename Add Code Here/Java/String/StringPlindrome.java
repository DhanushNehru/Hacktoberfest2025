package String;

import java.util.Scanner;


public class StringPlindrome {
    public static boolean pelindrome(String str){
        int n = str.length();
        for(int i=0;i<n/2;i++){
            if(str.charAt(i)!=str.charAt(n-1-i)){
                return false;
            }
            else{
                return true;
            }
        }
        return true;
        
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Word: ");
        String str = sc.next();
        if(pelindrome(str)==true){
            System.out.println("Is Pellindrome");
        }
        else{
            System.out.println("Not pelidrome");
        }


    }
}
