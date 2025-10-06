import java.util.Scanner;

public class pelindrome {



    public static int reverse(int num){
        int reversenum=0;
        while(num>0){
            int lastdigit=num%10;
            num = num/10;
            reversenum = reversenum*10+lastdigit;
        }
        return reversenum;
    }
    public static boolean ispelindrome(int num){
        int reverse = reverse(num);
        
        if(reverse == num){
            return true;
        }
        else{
            return false;
        }  }      
        public static void main(String[] args)
    {   Scanner sc =new Scanner(System.in);
        System.out.print("Enter a Number: ");
        int a= sc.nextInt() ;      
        boolean ans = ispelindrome(a);
        System.out.println(ans);

    }
}

