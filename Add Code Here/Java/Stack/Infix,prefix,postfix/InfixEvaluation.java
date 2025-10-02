import java.util.Stack;

public class InfixEvaluation {// Node - infix and prefix are different...........
    public static int PerformOp(String str) {
        int n = str.length();
        Stack<Integer> st = new Stack<>();
        Stack<Character> charSt = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch - '0');
            } else if (charSt.isEmpty() || ch == '(') {
                charSt.push(ch);
            } else if (ch == ')') {
                while (charSt.peek() != '(') {
                    int v2 = st.pop();
                    int v1 = st.pop();
                    int res = 0;
                    char op = charSt.pop();
                    switch (op) {
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
                charSt.pop();
            } else {
                if ((ch == '+' || ch == '-')) {
                    while (!charSt.empty() && charSt.peek() != '(') {
                        int v2 = st.pop();
                        int v1 = st.pop();
                        int res = 0;
                        char op = charSt.pop();
                        switch (op) {
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
                    charSt.push(ch);
                } else if (ch == '*' || ch == '/') {
                    if (charSt.peek() == '+' || charSt.peek() == '-') {
                        charSt.push(ch);
                    } else {
                        int v2 = st.pop();
                        int v1 = st.pop();
                        int res = 0;
                        char op = charSt.pop();
                        switch (op) {
                            case '*':
                                res = v1 * v2;
                                break;
                            case '/':
                                res = v1 / v2;
                                break;
                        }
                        st.push(res);
                        charSt.push(ch);
                    }
                }
            }
        }
        int ans;
        while (!charSt.empty()) {
            int v2 = st.pop();
            int v1 = st.pop();
            char op = charSt.pop();
            int res = 0;
            switch (op) {
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
        ans = st.pop();
        return ans;
    }

    public static void main(String[] args) {
        String str = "8/2*(9+8)";
        int ans = PerformOp(str);
        System.out.println(ans);
    }
}