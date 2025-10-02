import java.util.Stack;

public class PostfixEvaluation {
    public static int EvaluatePostfix(String poststr) {
        int n = poststr.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = poststr.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch - '0');
            } else {
                int v2 = st.pop();
                int v1 = st.pop();
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
        String PostStr = "82/98+*";
        int ans = EvaluatePostfix(PostStr);
        System.out.println(ans);
    }
}
