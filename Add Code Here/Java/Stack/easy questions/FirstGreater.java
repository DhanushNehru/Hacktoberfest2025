import java.util.Stack;

public class FirstGreater {
    public static int[] FindGreater(int[] arr) {
        // this would take O(n^2)..............
        // int k = 0;
        // int n = arr.length;
        // for (int i = 1; i < n && k < n; i++) {
        // if (arr[k] < arr[i]) {
        // arr[k] = arr[i];
        // i = k + 1;
        // k++;
        // }
        // if (i == n - 1) {
        // arr[k] = -1;
        // i = k + 1;
        // k++;
        // }
        // }
        // return arr;

        // this would take lesser.................
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.empty() && arr[i] > st.peek()) {
                st.pop();
            }
            int x = arr[i];
            if (st.empty())
                arr[i] = -1;
            else
                arr[i] = st.peek();
            st.push(x);
        }
        return arr;
    }

    public static int[] FindGreaterRev(int[] arr) {
        // this is straight reversible method..........
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);
        for (int i = 0; i < n; i++) {

        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 1, 8, 6, 3, 4 };
        int[] ans = FindGreater(arr);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
