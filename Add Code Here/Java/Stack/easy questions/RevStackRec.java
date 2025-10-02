import java.util.Stack;

public class RevStackRec {
    public static Stack<Integer> ReverseStack(Stack<Integer> st, Stack<Integer> arr) {
        if (st.size() == 0) {
            return arr;
        }
        arr.push(st.pop());
        return ReverseStack(st, arr);
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
        // Stack<Integer> anr = new Stack<>();
        // Stack<Integer> ans = ReverseStack(st, anr);
        // Reverse(st);
        System.out.println(st);
    }
}
