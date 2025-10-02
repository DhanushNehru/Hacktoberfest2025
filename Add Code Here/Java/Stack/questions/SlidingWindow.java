import java.util.Stack;

public class SlidingWindow {
    public static int[] ReturnMax(int[] arr, int k) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[n];
        nge[n - 1] = -1;
        int[] ans = new int[n - k + 1];
        st.push(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (!st.empty() && arr[i] > arr[st.peek()]) {
                st.pop();
            }
            if (st.empty())
                nge[i] = -1;
            else if (!st.empty()) {
                nge[i] = st.peek();
            }
            st.push(i);
        }
        int t2 = k - 1;
        int t1 = 0;
        int j = 0;
        int max = 0;
        // while (t2 < n) {
        // i = t1;
        // while (i <= t2) {
        // if (nge[i] == -1 || nge[i] > t2) {
        // max = arr[i];
        // break;
        // } else if (nge[i] <= t2) {
        // i = nge[i];
        // }
        // }
        // ans[j++] = max;
        // max = 0;
        // t1++;
        // t2++;
        // }
        int i = 0;
        while (i < n && t2 < n) {
            if (nge[i] == -1 || nge[i] > t2) {
                max = arr[i];
                ans[j++] = max;
                t2++;
            } else if (nge[i] <= t2) {
                i = nge[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        int[] ans = ReturnMax(arr, k);
        for (int i : ans)
            System.out.print(i + " ");
    }
}
