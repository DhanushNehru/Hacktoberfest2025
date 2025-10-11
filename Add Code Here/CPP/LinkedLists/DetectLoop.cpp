#include <iostream>
using namespace std;

// Node structure
struct Node {
    int data;
    Node* next;
    Node(int val) : data(val), next(nullptr) {}
};

// Function to detect loop in linked list (Floyd’s Algorithm)
bool detectLoop(Node* head) {
    Node* slow = head;
    Node* fast = head;

    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            return true;
    }
    return false;
}

// Function to create a loop in the linked list
void createLoop(Node* head, int position) {
    if (position == 0) return;

    Node* loopNode = head;
    for (int i = 1; i < position && loopNode; i++) {
        loopNode = loopNode->next;
    }

    if (!loopNode) return;

    Node* temp = head;
    while (temp->next)
        temp = temp->next;

    temp->next = loopNode;
}

void printList(Node* head) {
    Node* temp = head;
    int count = 0;
    while (temp && count < 20) {
        cout << temp->data << " -> ";
        temp = temp->next;
        count++;
    }
    cout << "NULL" << endl;
}

int main() {
    int n;
    cout << "Enter number of nodes in linked list: ";
    cin >> n;

    if (n <= 0) {
        cout << "List cannot be empty!" << endl;
        return 0;
    }

    Node* head = nullptr;
    Node* tail = nullptr;

    cout << "Enter " << n << " elements: ";
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        Node* newNode = new Node(val);

        if (!head)
            head = tail = newNode;
        else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    int pos;
    cout << "Enter position to create loop (0 for no loop): ";
    cin >> pos;

    createLoop(head, pos);

    if (detectLoop(head))
        cout << "Loop detected in linked list!" << endl;
    else
        cout << "No loop detected in linked list." << endl;

    cout << "\n(Printing first 20 nodes for safety)\n";
    printList(head);

    return 0;
}