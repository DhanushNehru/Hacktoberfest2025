import java.util.Stack;

public class PushAtBottRec {
    public static void InsetAtBottom(Stack<Integer> st, int elem) {
        if (st.size() == 0) {
            st.push(100);
            return;
        }
        int x = st.peek();
        st.pop();
        InsetAtBottom(st, elem);
        st.push(x);
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        st.push(6);
        st.push(7);
        InsetAtBottom(st, 100);
        System.out.println(st);
    }
}