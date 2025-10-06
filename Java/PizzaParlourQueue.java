import java.util.Scanner;

class CircularQueue {
    private String[] queue;
    private int front, rear, capacity;
    private int size;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(String order) {
        if (isFull()) {
            System.out.println("Order queue is full! Cannot accept new orders.");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;
        System.out.println("Order placed: " + order);
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println("No orders to serve.");
            return null;
        }
        String order = queue[front];
        front = (front + 1) % capacity;
        size--;
        return order;
    }

    public void displayOrders() {
        if (isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }
        System.out.println("Current orders in the queue:");
        for (int i = 0; i < size; i++) {
            System.out.println(queue[(front + i) % capacity]);
        }
    }
}

public class PizzaParlourQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum number of orders (capacity of queue): ");
        int capacity = scanner.nextInt();
        CircularQueue orderQueue = new CircularQueue(capacity);

        while (true) {
            System.out.println("\nPizza Parlour Menu:");
            System.out.println("1. Place an order");
            System.out.println("2. Serve an order");
            System.out.println("3. Display current orders");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter your order: ");
                    String order = scanner.nextLine();
                    orderQueue.enqueue(order);
                    break;
                case 2:
                    String servedOrder = orderQueue.dequeue();
                    if (servedOrder != null) {
                        System.out.println("Serving order: " + servedOrder);
                    }
                    break;
                case 3:
                    orderQueue.displayOrders();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
