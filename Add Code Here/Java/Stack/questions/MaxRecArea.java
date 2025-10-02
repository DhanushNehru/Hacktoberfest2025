import java.util.Stack;

public class MaxRecArea {
    public static int ReturnMaxArea(int[] height) {
        int maxArea = 0;
        int n = height.length;
        int[] Nse = new int[n];
        int[] Pse = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {// For Nse...........
            while (!st.empty() && height[i] < height[st.peek()]) {
                st.pop();
            }
            if (st.empty()) {
                Nse[i] = n;
            } else {
                Nse[i] = st.peek();
            }
            st.push(i);
        }
        st = new Stack<>();
        for (int i = 0; i < n; i++) {// For Pse................
            while (!st.empty() && height[i] < height[st.peek()]) {
                st.pop();
            }
            if (st.empty()) {
                Pse[i] = 0;
            } else {
                Pse[i] = st.peek();
            }
            st.push(i);
        }
        for (int i = 0; i < n; i++) {
            int x = height[i] * ((Nse[i] - Pse[i]) - 1);
            if (x > maxArea) {
                maxArea = x;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 4, 6, 3, 5 };
        int ans = ReturnMaxArea(arr);
        System.out.println(ans);
    }
}