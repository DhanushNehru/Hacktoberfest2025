import java.util.Stack;

public class Concept {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(6);
        st.push(7);
        st.push(9);
        st.push(0);
        System.out.println(st);
        System.out.println("The peek is or the outer element is: " + st.peek());
        st.pop();
        System.out.println(st);
        System.out.println("The peek is or the outer element is: " + st.peek());
        System.out.println(st.size());
    }
}