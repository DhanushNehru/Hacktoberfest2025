import java.util.Stack;

public class CopyStack {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        System.out.println(st);
        Stack<Integer> ht = new Stack<>();
        while (st.size() > 0) {// for reverse using another stack............
            int x = st.peek();
            ht.push(x);
            st.pop();
        }
        System.out.println(ht);
        Stack<Integer> gt = new Stack<>();
        while (ht.size() > 0) {// for copy................
            int x = ht.peek();
            gt.push(x);
            ht.pop();
        }
        System.out.println(gt);
        while (gt.size() > 0) {// for reversing inplace by using extra space.................
            int x = gt.peek();
            st.push(x);
            gt.pop();
        }
        System.out.println(st);
    }
}
