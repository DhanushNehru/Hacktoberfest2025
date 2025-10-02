import java.util.Stack;

public class Stock {// this code is inplace..............
    public static class pair {
        int data;
        int idx;

        public pair(int data, int idx) {
            this.data = data;
            this.idx = idx;
        }
    }

    public static int[] FindAnalysis(int[] arr) {
        // Stack<pair> st = new Stack<>();
        int n = arr.length;
        // for (int i = 0; i < n; i++) {
        // while (!st.empty() && st.peek().data < arr[i]) {
        // st.pop();
        // }
        // int x = arr[i];
        // if (!st.empty()) {
        // arr[i] = i - st.peek().idx;// To save the main ans that is diff b/w the PGE
        // and current index.......
        // } else {
        // arr[i] = i + 1;// To save the main ans of that idx which does't have any PGE
        // means -1...so we
        // // have saved here i+1........
        // }
        // pair p1 = new pair(x, i);
        // st.push(p1);
        // }
        // return arr;

        Stack<Integer> st = new Stack<>();// Instead of pair we can use directly the index and pge array.... but as we
                                          // know that we require PGE array which require more space..........
        int[] pge = new int[n];
        pge[0] = 1;
        st.push(0);
        for (int i = 1; i < n; i++) {// for pse........
            while (!st.empty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            if (!st.empty()) {
                pge[i] = i - st.peek();
            } else {
                pge[i] = i + 1;
            }
            st.push(i);
        }
        return pge;
    }

    public static void main(String[] args) {
        // this is code for giving the data about the price of a stock (how many times
        // it is
        // higher than the all previous stock prices, or how many times it is low)
        int[] arr = { 100, 80, 60, 70, 60, 75, 85 };
        int[] ans = FindAnalysis(arr);
        for (int i : ans)
            System.out.print(i + " ");
    }
}
