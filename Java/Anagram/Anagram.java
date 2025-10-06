import java.util.*;

public class Anagram{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String s1,s2;
        System.out.println("Enter string 1");
        s1=sc.next();
        System.out.println("Enter string 2");
        s2=sc.next();

        char arr1[] = s1.toCharArray();
        char arr2[] = s2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        boolean res = Arrays.equals(arr1,arr2);

        if(res==true)
            System.out.println("Anagram");
        else
            System.out.println("Not Anagram");


    }
}