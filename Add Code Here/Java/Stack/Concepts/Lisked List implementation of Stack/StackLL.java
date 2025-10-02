class Stack {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head;
    int size = 0;

    public int pop() {
        if (head == null) {
            System.out.println("Underflow Spotted!");
            return -1;
        } else if (head.next == null) {
            Node temp = head;
            head = null;
            return temp.data;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        Node value = temp.next;
        temp.next = null;
        size--;
        return value.data;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Underflow Spotted!");
            return -1;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.data;
    }

    public void push(int elem) {
        Node ele = new Node(elem);
        if (head == null) {
            head = ele;
            size++;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = ele;
        size++;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void size() {
        System.out.println(size);
    }
}
=
public class StackLL {
    public static void main(String[] args) {
        Stack st = new Stack();
        st.push(0);
        st.push(1);
        st.push(3);
        st.display();
        st.size();
        st.pop();
        st.display();
        st.size();
    }
}