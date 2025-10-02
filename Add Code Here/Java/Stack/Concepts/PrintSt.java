import java.util.*;

public class PrintSt {
    public static void Display(Stack<Integer> st) {
        if (st.size() == 0)
            return;
        int x = st.peek();
        st.pop();
        Display(st);
        System.out.println(x);
        st.push(x);
        return;
    }

    public static void DisplayVmro(Stack<Integer> st) {
        int n = st.size();
        int[] arr = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = st.pop();
        }
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
            st.push(arr[i]);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        Display(st);
        // DisplayVmro(st);
        System.out.println(st);
    }
}
