// This program demonstrates a custom Priority Queue in Java using a Min-Heap.Users can insert integers with priorities and extract elements in priority order.
 

import java.util.ArrayList;
import java.util.Scanner;

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(int val) {
        heap.add(val);
        int i = heap.size() - 1;

        while (i != 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extractMin() {
        if (heap.size() == 0) return -1;
        if (heap.size() == 1) return heap.remove(0);

        int root = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        minHeapify(0);
        return root;
    }

    private void minHeapify(int i) {
        int l = left(i), r = right(i), smallest = i;
        if (l < heap.size() && heap.get(l) < heap.get(smallest)) smallest = l;
        if (r < heap.size() && heap.get(r) < heap.get(smallest)) smallest = r;

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public void display() {
        System.out.println(heap);
    }
}

public class AdvancedPriorityQueue {
    public static void main(String[] args) {
        MinHeap pq = new MinHeap();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Advanced Priority Queue (Min-Heap) ===");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert element");
            System.out.println("2. Extract minimum");
            System.out.println("3. Display heap");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number to insert: ");
                    int num = sc.nextInt();
                    pq.insert(num);
                    System.out.println(num + " inserted!");
                    break;
                case 2:
                    int min = pq.extractMin();
                    if (min == -1) System.out.println("Heap is empty!");
                    else System.out.println("Extracted: " + min);
                    break;
                case 3:
                    System.out.println("Current Heap: ");
                    pq.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}
