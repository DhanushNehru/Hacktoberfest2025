class Solution {
public:
      bool isValid(string s) {
        stack<char> st;
        for (int c : s) {
            if (c == '{' || c == '(' || c == '[') {  
                st.push(c);
            } else { 
                if (st.empty())  
                    return false;
                if ((c == ')' && st.top() != '(') ||
                    (c == '}' && st.top() != '{') ||
                    (c == ']' && st.top() != '[')) {
                    return false;
                } else
                    st.pop();
            }
        }
        return st.empty();
    }
};
