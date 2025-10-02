import java.util.Stack;

public class PostfixToInfixConversion {
    public static String ConvertToInfix(String postfix) {
        int n = postfix.length();
        Stack<String> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = postfix.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch + "");
            } else {
                String v2 = st.pop();
                String v1 = st.pop();
                String comb = v1 + ch + v2;
                String res = "(" + comb + ")";
                st.push(res);
            }
        }
        return st.peek();
    }

    public static void main(String[] args) {// "8/2*(9+8)".....expected.........
        String Postfix = "82/98+*";
        String ans = ConvertToInfix(Postfix);
        System.out.println(ans);
    }
}
