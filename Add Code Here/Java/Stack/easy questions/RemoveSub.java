import java.util.Stack;

public class RemoveSub {
    public static int[] RemoveSubseq(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int i;
        for (i = 0; i < n; i++) {
            int x = arr[i];
            if (st.empty() || st.peek() != x) {
                st.push(x);
            } else {
                while (i + 1 < n && arr[i] == arr[i + 1]) {
                    i++;
                }
                st.pop();
                // if (i == n - 1 || arr[i] != arr[i + 1]){st.pop();}
                // instead of while condition we can
                // also do this...................
            }
        }
        int[] ans = new int[st.size()];
        for (i = st.size() - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
                6, 6, 6, 7, 7, 7, 8 };
        int[] ans = RemoveSubseq(arr);
        for (int i : ans)
            System.out.print(i + " ");
    }
}
