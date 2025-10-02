public class ArrayImplementation {
    public static class Queue {// stack is absolute inversal of this implementation.......
        int[] arr = new int[100];
        int front = 0;// For FIFO.................
        int rear = 0;// For last element or for rear element......
        int size = 0;

        void add(int elem) {
            if (rear < arr.length) {
                arr[rear++] = elem;
                size++;
            } else {
                System.out.println("Queue is full");
                return;
            }
        }

        int remove() {
            if (size > 0) {
                size--;
                int x = front;
                front++;
                return arr[x];
            } else {
                System.out.print("");
                return -1;
            }
        }

        int peek() {
            return front;
        }

        void display() {
            for (int i = front; i < rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue qu = new Queue();
        qu.add(0);
        qu.add(100);
        qu.add(2);
        qu.add(3);
        qu.add(4);
        qu.display();
        System.out.println(qu.size);
        System.out.println(qu.peek());
        System.out.println("Remove : " + qu.remove());
        qu.display();
        System.out.println(qu.remove());
        qu.display();
    }
}
