import java.util.Stack;

public class InfixtoPrefix {
    public static String PerformOperation(String str) {
        Stack<String> st = new Stack<>();
        Stack<String> op = new Stack<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch + "");
            } else if (op.empty() || ch == '(') {
                op.push(ch + "");
            } else if (ch == ')') {
                while (!op.peek().equals("(")) {
                    String v2 = st.pop();
                    String v1 = st.pop();
                    String opr = op.pop();
                    String res;
                    res = opr + v1 + v2;
                    st.push(res);
                }
                op.pop();
            } else {
                if (ch == '+' || ch == '-') {
                    while (!op.empty() && !op.peek().equals("(")) {
                        String v2 = st.pop();
                        String v1 = st.pop();
                        String opr = op.pop();
                        String res;
                        res = opr + v1 + v2;
                        st.push(res);
                    }
                    op.push(ch + "");
                } else if (ch == '*' || ch == '/') {
                    if (op.peek().equals("*") || op.peek().equals("/")) {
                        while ((!op.isEmpty() && !op.peek().equals("(")) && (op.peek().equals("*")
                                || op.peek().equals("/"))) {
                            String v2 = st.pop();
                            String v1 = st.pop();
                            String opr = op.pop();
                            String res;
                            res = opr + v1 + v2;
                            st.push(res);
                        }
                        op.push(ch + "");
                    } else if (op.peek().equals("+") || op.peek().equals("-"))
                        op.push(ch + "");
                }
            }
        }
        while (!op.empty()) {
            String v2 = st.pop();
            String v1 = st.pop();
            String opr = op.pop();
            String res;
            res = opr + v1 + v2;
            st.push(res);
        }
        return st.pop();
    }

    public static void main(String[] args) {
        String str = "(9-(5+3))*4/6";
        String ans = PerformOperation(str);
        System.out.println(ans);
    }
}
