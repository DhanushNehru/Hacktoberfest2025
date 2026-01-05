// C++ program to implement singly linked list using a class
#include <iostream>
using namespace std;

// Node class to represent a node of the linked list.
class Node {
  public:
    int data;
    Node *next;

    // Default constructor
    Node() {
        data = 0;
        next = NULL;
    }

    // Parameterised Constructor
    Node(int data) {
        this->data = data;
        this->next = NULL;
    }
};

// Linked list class to implement a singly linked list
class Linkedlist {
    Node *head;

  public:
    // Default constructor
    Linkedlist() {
        head = NULL;
    }

    // Function to insert a node at the start of the
    // linked list
    void insertAtHead(int data) {

        // Create the new Node
        Node *newNode = new Node(data);

        // Assign to head of the list is empty
        if (head == NULL) {
            head = newNode;
            return;
        }

        // Insert the newly created linked list at the head
        newNode->next = this->head;
        this->head = newNode;
    }

    // Function to print the linked list.
    void print() {
        Node *temp = head;

        // Check for empty list
        if (head == NULL) {
            cout << "List empty" << endl;
            return;
        }

        // Traverse the list
        while (temp != NULL) {
            cout << temp->data << " ";
            temp = temp->next;
        }
    }
};

int main() {

    // Creating a LinkedList object
    Linkedlist list;

    // Inserting nodes
    list.insertAtHead(4);
    list.insertAtHead(3);
    list.insertAtHead(2);
    list.insertAtHead(1);

    cout << "Elements of the list are: ";

    // Print the list
    list.print();
    cout << endl;

    return 0;
}
