import java.util.Stack;

public class PrefixEvaluation {
    public static int EvaluatePrefix(String prefix) {
        int n = prefix.length();
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            char ch = prefix.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch - '0');
            } else {
                int v1 = st.pop();
                int v2 = st.pop();
                int res = 0;
                switch (ch) {
                    case '+':
                        res = v1 + v2;
                        break;
                    case '-':
                        res = v1 - v2;
                        break;
                    case '*':
                        res = v1 * v2;
                        break;
                    case '/':
                        res = v1 / v2;
                        break;
                }
                st.push(res);
            }
        }
        return st.peek();
    }

    public static void main(String[] args) {
        String prefix = "*/82+98";
        int ans = EvaluatePrefix(prefix);
        System.out.println(ans);
    }
}
