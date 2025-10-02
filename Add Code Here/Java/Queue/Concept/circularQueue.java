public class circularQueue {
    public static class Cq {
        private int[] arr = new int[5];
        private int size = 0;
        private int f = 0;
        private int r = 0;
        private int n = arr.length;

        void add(int val) {
            if (size == 0) {
                arr[0] = val;
                size++;
            } else if (size == n) {
                System.out.println("Queue is full");
                return;
            } else if (r < n - 1) {
                arr[++r] = val;
                size++;
            } else if (r == n - 1) {
                r = 0;
                arr[0] = val;
                size++;
            }
        }

        int remove() {
            if (size == 0) {
                System.out.println("Queue is empty vmro");
                return -1;
            } else {
                int x = arr[f];
                f++;
                size--;
                return x;
            }
        }

        int peek() {
            if (size == 0) {
                System.out.println("Queue is full");
                return -1;
            }
            return arr[f];
        }

        void display() {
            if (f <= r) {
                for (int i = f; i <= r; i++) {
                    System.out.print(arr[i] + " ");
                }
            } else if (r < f) {
                for (int i = f; i <= n - 1; i++) {
                    System.out.print(arr[i] + " ");
                }
                for (int i = 0; i <= r; i++) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Cq cq = new Cq();
        cq.add(100);
        cq.add(200);
        cq.add(300);
        cq.add(400);
        cq.remove();
        System.out.println("Peek is " + cq.peek());
        cq.add(500);
        cq.remove();
        cq.add(1000);
        cq.add(2000);
        // cq.add(600);
        cq.display();
        System.out.println("Peek is " + cq.peek());
    }
}