import java.util.Stack;
import java.util.Scanner;

public class toReverse {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many elements do you want in the stack");
        int n = sc.nextInt();
        System.out.println("Define the elements");
        for (int i = 1; i <= n; i++) {// to input element in the stack..............
            int x = sc.nextInt();
            st.push(x);
        }
        st.add(3);// this also adds elements....
        System.out.println("this is first stack ");
        System.out.println(st);
        Stack<Integer> ht = new Stack<>();
        while (st.size() > 0) {// to get elements and transfer them to another stack to reverse..............
            int x = st.peek();
            ht.push(x);
            st.pop();
        }
        System.out.println("this is first stack after transfering elements to another stack...");
        System.out.println(st);
        System.out.println("This is reversed stack ");
        System.out.println(ht);
    }
}
