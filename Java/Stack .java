public class Stack {
    private final int[] data;
    private int top = -1;

    public Stack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.data = new int[capacity];
    }

    public void push(int x) {
        if (top == data.length - 1) throw new IllegalStateException("Overflow");
        data[++top] = x;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Underflow");
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Empty");
        return data[top];
    }

    public boolean isEmpty() { return top == -1; }

    public int size() { return top + 1; }

    public static void main(String[] args) {
        Stack s = new Stack(5);
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.peek()); // 30
        System.out.println(s.pop());  // 30
        System.out.println(s.size()); // 2
    }
}
