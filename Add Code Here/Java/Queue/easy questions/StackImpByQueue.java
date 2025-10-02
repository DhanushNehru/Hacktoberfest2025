import java.util.LinkedList;
import java.util.Queue;

public class StackImpByQueue {
    public static class Stack {
        Queue<Integer> qu = new LinkedList<>();
        int size = 0;

        void push(int val) {
            qu.add(val);
            size++;
        }

        int pop() {
            if (qu.isEmpty()) {
                System.out.println(" Stack is empty ");
                return -1;
            }
            int i = 1;
            while (i < qu.size()) {
                qu.add(qu.remove());
                i++;
            }
            int x = qu.remove();
            size--;
            return x;
        }

        int peek() {
            if (qu.isEmpty()) {
                System.out.println(" Stack is empty ");
                return -1;
            }
            int i = 1;
            while (i < qu.size()) {
                qu.add(qu.remove());
                i++;
            }
            return qu.element();
        }

        boolean empty() {
            if (size == 0) {
                return true;
            }
            return false;
        }

        int Size() {
            System.out.println(" The size is " + size);
            return size;
        }

        void display() {
            System.out.println(qu);
        }
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        System.out.println(st.empty());
        st.push(100);
        st.push(200);
        st.push(300);
        st.push(400);
        st.push(500);
        st.Size();
        st.display();
        st.pop();
        st.display();
        st.Size();
        System.out.println(st.peek());
        System.out.println(st.empty());
    }
}
