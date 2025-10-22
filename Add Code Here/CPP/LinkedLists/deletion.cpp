#include <iostream>
using namespace std;

// Node structure
struct Node {
    int data;
    Node* next;
    Node(int val) : data(val), next(nullptr) {}
};

// Insert at the end
void insertEnd(Node*& head, int val) {
    Node* newNode = new Node(val);
    if (!head) {
        head = newNode;
        return;
    }
    Node* temp = head;
    while (temp->next) temp = temp->next;
    temp->next = newNode;
}

// Print the linked list
void printList(Node* head) {
    while (head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << "NULL" << endl;
}

// Delete first node
void deleteBeginning(Node*& head) {
    if (!head) return;
    Node* temp = head;
    head = head->next;
    delete temp;
}

// Delete last node
void deleteEnd(Node*& head) {
    if (!head) return;
    if (!head->next) {
        delete head;
        head = nullptr;
        return;
    }
    Node* temp = head;
    while (temp->next->next) temp = temp->next;
    delete temp->next;
    temp->next = nullptr;
}

// Delete node by value (first occurrence)
void deleteValue(Node*& head, int val) {
    if (!head) return;

    // If head needs to be deleted
    if (head->data == val) {
        deleteBeginning(head);
        return;
    }

    Node* temp = head;
    while (temp->next && temp->next->data != val) {
        temp = temp->next;
    }

    if (temp->next) {
        Node* nodeToDelete = temp->next;
        temp->next = temp->next->next;
        delete nodeToDelete;
    }
}

int main() {
    Node* head = nullptr;

    // Insert elements
    insertEnd(head, 10);
    insertEnd(head, 20);
    insertEnd(head, 30);
    insertEnd(head, 40);

    cout << "Original list: ";
    printList(head);

    deleteBeginning(head);
    cout << "After deleting beginning: ";
    printList(head);

    deleteEnd(head);
    cout << "After deleting end: ";
    printList(head);

    deleteValue(head, 20);
    cout << "After deleting value 20: ";
    printList(head);

    return 0;
}
