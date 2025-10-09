#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
} *head = NULL;

// Create linked list from user input
void create(int n) {
    int i, value;
    struct Node *t, *last;

    if (n <= 0) return;

    printf("Enter element 1: ");
    scanf("%d", &value);

    head = (struct Node *)malloc(sizeof(struct Node));
    head->data = value;
    head->next = NULL;
    last = head;

    for (i = 1; i < n; i++) {
        printf("Enter element %d: ", i + 1);
        scanf("%d", &value);

        t = (struct Node *)malloc(sizeof(struct Node));
        t->data = value;
        t->next = NULL;
        last->next = t;
        last = t;
    }
}

// Display linked list
void Display(struct Node *p) {
    if (p == NULL) {
        printf("List is empty.\n");
        return;
    }
    while (p != NULL) {
        printf("%d ", p->data);
        p = p->next;
    }
    printf("\n");
}

// Insert a node at the end
void InsertEnd(int value) {
    struct Node *t = (struct Node *)malloc(sizeof(struct Node));
    t->data = value;
    t->next = NULL;

    if (head == NULL) {
        head = t;
        return;
    }

    struct Node *p = head;
    while (p->next != NULL)
        p = p->next;
    p->next = t;
}

// Delete a node by value
void Delete(int value) {
    struct Node *p = head, *q = NULL;

    if (head == NULL) {
        printf("List is empty.\n");
        return;
    }

    if (head->data == value) {
        head = head->next;
        free(p);
        return;
    }

    while (p != NULL && p->data != value) {
        q = p;
        p = p->next;
    }

    if (p != NULL) {
        q->next = p->next;
        free(p);
    } else {
        printf("%d not found in the list.\n", value);
    }
}

// Search a node by value
int Search(int value) {
    struct Node *p = head;
    int pos = 1;
    while (p != NULL) {
        if (p->data == value) return pos;
        p = p->next;
        pos++;
    }
    return -1; // Not found
}

int main() {
    int n, value, pos, choice, times;

    printf("Enter number of elements in linked list: ");
    scanf("%d", &n);
    create(n);

    printf("Original List: ");
    Display(head);

    printf("Enter number of operations you want to perform: ");
    scanf("%d", &times);

    for (int i = 0; i < times; i++) {
        printf("\nMenu:\n");
        printf("1. Insert at end\n");
        printf("2. Delete a value\n");
        printf("3. Search a value\n");
        printf("4. Display list\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter value to insert: ");
                scanf("%d", &value);
                InsertEnd(value);
                break;
            case 2:
                printf("Enter value to delete: ");
                scanf("%d", &value);
                Delete(value);
                break;
            case 3:
                printf("Enter value to search: ");
                scanf("%d", &value);
                pos = Search(value);
                if (pos != -1)
                    printf("%d found at position %d\n", value, pos);
                else
                    printf("%d not found\n", value);
                break;
            case 4:
                printf("Current List: ");
                Display(head);
                break;
            default:
                printf("Invalid choice!\n");
        }
    }

    printf("\nFinal List: ");
    Display(head);
    return 0;
}
