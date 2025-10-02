public class LinklistImplementation {
    public static class Node {// this is very simple Queue implementation through Linked list............
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Queue {
        Node head;// for FIFO..............
        int size = 0;
        Node tail;// for rear or Last element..........

        void add(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = newNode;
                tail = head;
                size++;
            } else {
                tail.next = newNode;
                tail = tail.next;// or we can alos write tail = newNode........
                size++;
            }
        }

        int remove() {
            if (head == null) {
                System.out.println("Stack is empty vmro !");
                return -1;
            }
            int elem = head.data;
            head = head.next;
            size--;
            return elem;
        }

        int peek() {
            if (head == null) {
                System.out.println("Stack is empty vmro !");
                return -1;
            }
            return head.data;
        }

        void display() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        int Size() {
            System.out.println(size);
            return size;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(20);
        q.add(21);
        q.add(22);
        q.add(23);
        q.add(24);
        q.add(25);
        q.display();
        System.out.println(q.peek());
        q.Size();
        q.remove();
        q.display();
        System.out.println(q.peek());
        q.Size();
    }
}
