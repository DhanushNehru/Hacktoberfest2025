package Java;
import java.util.*;
public class Stringope {
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        StringBuilder sb=new StringBuilder();
        sb.append(str);

        for(int i=1;i<sb.length() && sb.length()>2;i++){

            int temp = ((sb.charAt(i)-'0') + (sb.charAt(i-1)-'0'))%10;
            sb.setCharAt(i-1, (char)(temp+'0'));

            if(i==sb.length()-1){
                sb.deleteCharAt(i);
                i=0;
            } 
        }

        if(sb.charAt(0)==sb.charAt(1))
            System.out.println(true);
        else
            System.out.println(false);

        
    }
}
