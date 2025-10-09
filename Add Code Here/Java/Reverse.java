class LL {
    Node head;
    private int size;

    LL() {
        this.size = 0;
    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
            size++;
        }
    }

    // add at first position
    public void Addfirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // add at last position
    public void Addlast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    // delete operation
    public void deletefirst() {
        if (head == null) {
            System.out.println("THE LIST IS EMPTY");
            return;
        }
        size--;
        head = head.next;
    }

    public void deletelast() {
        if (head == null) {
            System.out.println("THE LIST IS EMPTY");
            return;
        }
        size--;
        if (head.next == null) {
            head = null;
            return;
        }
        Node secondlast = head;
        Node lastNode = head.next;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
            secondlast = secondlast.next;
        }
        secondlast.next = null;
    }

    public void printlist() {
        if (head == null) {
            System.out.print("list empty");
            return;
        }

        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.data + " ---> ");
            currNode = currNode.next;
        }

        System.out.println("NULL");
    }

    public void reverseIterate() {
        if (head == null || head.next == null) {
            return;
        }
        Node prevNode = head;
        Node currNode = head.next;
        while (currNode != null) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;

            // update
            prevNode = currNode;
            currNode = nextNode;
        }
        head.next = null;
        head = prevNode;
    }

    public int printSize() {
        return size;
    }

    public static void main(String args[]) {
        LL list = new LL();
        list.Addfirst(3);
        list.Addfirst(2);
        list.Addfirst(1);
        list.printlist();
        list.Addlast(4);
        list.Addlast(5);
        list.printlist();
        list.deletefirst();
        list.printlist();
        list.deletelast();
        list.printlist();
        System.out.println(list.printSize());
        list.reverseIterate();
        list.printlist();
    }
}
