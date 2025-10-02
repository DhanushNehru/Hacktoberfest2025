class Stack {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head = null;
    int size = 0;

    public void push(int elem) {
        Node temp = new Node(elem);
        temp.next = head;
        head = temp;
        size++;
    }

    public int pop() {
        if (head == null) {
            System.out.println("Underflow Spotted!");
            return -1;
        }
        int x = head.data;
        head = head.next;
        size--;
        return x;
    }

    public int peek() {
        if (head == null) {
            System.out.println("Underflow Spotted!");
            return -1;
        }
        return head.data;
    }

    public int size() {
        return size;
    }

    public void display(Stack st) {
        if (st.size() == 0) {
            System.out.println();
            return;
        }
        int x = st.pop();
        display(st);
        System.out.print(x + " ");
        st.push(x);
    }
}

public class LL {
    public static void main(String[] args) {
        Stack st = new Stack();
        st.push(0);
        st.push(1);
        st.push(5);
        st.push(9);
        // st.display(st);
        st.pop();
        // st.display(st);
        st.pop();
        st.pop();
        st.pop();
        st.display(st);
        // st.pop();
    }
}