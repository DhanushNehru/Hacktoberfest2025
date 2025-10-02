import java.util.Stack;

public class RevStackRecur {
    public static void PushAtBottRec(Stack<Integer> st, int elem) {
        if (st.size() == 0) {
            st.push(elem);
            return;
        }
        int x = st.pop();
        PushAtBottRec(st, elem);
        st.push(x);
    }

    public static void ReverseStack(Stack<Integer> st) {
        if (st.size() == 0) {
            return;
        }
        int x = st.pop();
        ReverseStack(st);
        PushAtBottRec(st, x);
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
        ReverseStack(st);
        System.out.println(st);
    }
}