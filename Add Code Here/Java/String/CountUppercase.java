package String;
import java.util.Scanner;
public class CountUppercase {
//checking the charachters are vovels or not
    public static int isLowerCase(String str){
        int len = str.length();
        int vowels= 0;
        for(int i=0;i<len;i++){
            char ch = str.charAt(i);
            if(Character.isLowerCase(ch)==true){
                if(ch== 'a' || ch=='e' ||ch== 'i' ||ch=='o' || ch=='u' ){
                    vowels++;
                }
            }
        }
        
        return vowels;
    }

    public static int UpperCaseCount(String str){
        int len = str.length();
        int vowels= 0;
        for(int i=0;i<len;i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)==true){
                vowels++;
            }
        }
        
        return vowels;
    }
    public static int LowerCaseCount(String str){
        int len = str.length();
        int vowels= 0;
        for(int i=0;i<len;i++){
            char ch = str.charAt(i);
            if(Character.isLowerCase(ch)==true){
                vowels++;
            }
        }
        
        return vowels;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string : ");
        String str = sc.next();
        System.out.println("The no of vowels are: "+ isLowerCase(str));
        System.out.println("The no of Upper case letters are: "+ UpperCaseCount(str));
        System.out.println("The no of Lower case letters are: "+ LowerCaseCount(str));
    }
}
