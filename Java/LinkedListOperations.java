/**
 * Linked List Operations
 * Common operations on singly linked lists
 */
public class LinkedListOperations {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    /**
     * Insert at the beginning
     */
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Insert at the end
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * Delete from beginning
     */
    public void deleteFromBeginning() {
        if (head != null) {
            head = head.next;
        }
    }

    /**
     * Display the list
     */
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        LinkedListOperations list = new LinkedListOperations();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.display();
        list.deleteFromBeginning();
        list.display();
    }
}
