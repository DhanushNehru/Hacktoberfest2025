
import java.util.*;

public class array_substring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter string: ");
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        
            String substring = S.substring(start, end);

        // Printing the substring
    
        System.out.println(substring);
    }
}

