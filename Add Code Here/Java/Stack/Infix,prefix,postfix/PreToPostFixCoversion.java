import java.util.Stack;

public class PreToPostFixCoversion {
    public static String ConvertToPostfix(String prefix) {
        int n = prefix.length();
        Stack<String> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            char ch = prefix.charAt(i);
            if ((int) ch >= 48 && (int) ch <= 57) {
                st.push(ch + "");
            } else {
                String v1 = st.pop();
                String v2 = st.pop();
                String res = v1 + v2 + ch + "";
                st.push(res);
            }
        }
        return st.peek();
    }

    public static void main(String[] args) {// 953+-4*6/ - expected output..........
        String prefix = "/*-9+5346";
        String ans = ConvertToPostfix(prefix);
        System.out.println(ans);
    }
}
