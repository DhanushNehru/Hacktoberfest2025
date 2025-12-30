import java.util.*;

public class MinHeap {
    private List<Integer> heap;

    // Constructor
    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Get parent and children indices
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    // Swap elements
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Insert new value
    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    // Move the inserted node up to maintain heap property
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Remove and return minimum element (root)
    public int removeMin() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    // Restore heap property by moving down
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left) < heap.get(smallest))
            smallest = left;
        if (right < heap.size() && heap.get(right) < heap.get(smallest))
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // Get minimum value
    public int getMin() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    // Display heap
    public void display() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinHeap minHeap = new MinHeap();

        while (true) {
            System.out.println("\n1. Insert  2. Remove Min  3. Get Min  4. Display  5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    minHeap.insert(val);
                    break;
                case 2:
                    try {
                        System.out.println("Removed Min: " + minHeap.removeMin());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Current Min: " + minHeap.getMin());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    minHeap.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
