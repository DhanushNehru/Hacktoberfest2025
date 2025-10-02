import java.util.Scanner;
import java.util.Stack;

public class Insert {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        System.out.println(st);
        System.out.println("Which element and at which index you want to insert ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Define idx and element");
        int idx = sc.nextInt();
        int elem = sc.nextInt();
        Stack<Integer> ht = new Stack<>();
        while (st.size() > idx) {
            ht.push(st.pop());// this statement can also be used instead of peek....and blah blah blah.......
        }
        st.push(elem);
        while (ht.size() > 0) {
            st.push(ht.pop());
        }
        System.out.println(st);
    }
}
