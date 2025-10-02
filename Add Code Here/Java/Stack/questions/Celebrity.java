import java.util.Stack;

public class Celebrity {
    public static int celebrity(int M[][], int n) {
        // int n = mat.length;
        int canCeleb = 0;// this one is robust method.............................rather than
                         // stack............
        for (int i = 1; i < n; i++) {
            if (M[canCeleb][i] == 1) {
                canCeleb = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (canCeleb == i)
                continue;
            if (M[canCeleb][i] == 1 || M[i][canCeleb] == 0) {
                return -1;
            }
        }
        return canCeleb;
        // Stack<Integer> st = new Stack<>();
        // for (int i = 0; i < n; i++) {
        // st.push(i);
        // }
        // while (st.size() > 1) {
        // int x = st.pop();
        // int y = st.pop();
        // if (M[x][y] == 0) {
        // st.push(x);
        // } else if (M[y][x] == 0) {
        // st.push(y);
        // }
        // }
        // if (st.size() == 0) {
        // return -1;
        // }
        // for (int i = 0; i < n; i++) {
        // if (M[st.peek()][i] == 1)
        // return -1;
        // }
        // for (int i = 0; i < n; i++) {
        // if (i == st.peek())
        // continue;
        // if (M[i][st.peek()] != 1)
        // return -1;
        // }
        // return st.peek();
    }

    public static void main(String[] args) {
        int[][] arr = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 1, 0 } };
        int n = arr.length;
        int ans = celebrity(arr, n);
        System.out.println(ans + " is celebrity......");
    }
}
