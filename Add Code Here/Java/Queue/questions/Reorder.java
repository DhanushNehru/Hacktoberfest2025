import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Reorder {
    public static void ReorderQueue(Queue<Integer> qu) {
        Stack<Integer> st = new Stack<>();
        int x = qu.size() / 2;
        int i = 1;
        while (i <= x) {
            st.push(qu.remove());
            i++;
        }
        while (!st.empty()) {
            qu.add(st.pop());
        }
        i = 1;
        while (i <= x) {
            st.push(qu.remove());
            i++;
        }
        while (!st.empty()) {
            qu.add(st.pop());
            qu.add(qu.remove());
        }
        while (!qu.isEmpty()) {
            st.push(qu.remove());
        }
        while (!st.empty())
            qu.add(st.pop());
    }

    public static void main(String[] args) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(1);
        qu.add(2);
        qu.add(3);
        qu.add(4);
        qu.add(5);
        qu.add(6);
        qu.add(7);
        qu.add(8);
        ReorderQueue(qu);
        System.out.println(qu);
    }
}