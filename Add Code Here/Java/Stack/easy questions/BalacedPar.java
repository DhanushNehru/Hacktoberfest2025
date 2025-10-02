import java.util.Stack;

public class BalacedPar {
    public static boolean CheckBalance(String str) {
        Stack<Character> st = new Stack<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                st.push('(');
            } else if (ch == ')') {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }
        if (st.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "(()()())";
        System.out.println(CheckBalance(str));
    }
}
