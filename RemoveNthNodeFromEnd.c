#include<stdio.h>
#include<stdlib.h>
struct Node{
    int data;
    struct Node * next;
};
struct Node * printLinkedList(struct Node * head)
{
    struct Node * ptr = head;
    do                                                              //size - endindex-1
    {
        printf("%d ", ptr->data);
        ptr = ptr->next;
    }while(ptr != NULL);
    printf("\n");
    return head;
}
struct Node * removeFromEnd(struct Node * head, int size, int endnode)
{
    struct Node * ptr = head;
    struct Node * p = head->next;
    int i = 0;
    while(i != (size - endnode - 1))
    {
        ptr = ptr->next;
        p = p->next;
        i++;
    }
    ptr->next = p->next;
    free(p);
    return head;

}
int main()
{
    struct Node * head = (struct Node *)malloc(sizeof(struct Node));
    struct Node * secondNode = (struct Node *)malloc(sizeof(struct Node));
    struct Node * thirdNode = (struct Node *)malloc(sizeof(struct Node));
    struct Node * fourthNode = (struct Node *)malloc(sizeof(struct Node));
    head->data = 5;
    head->next = secondNode;

    secondNode->data = 15;
    secondNode->next = thirdNode;

    thirdNode->data = 25;
    thirdNode->next = fourthNode;

    fourthNode->data = 50;
    fourthNode->next = NULL;
    
    int size = 4;
    printLinkedList(head);
    head = removeFromEnd(head, size , 2);
    printLinkedList(head);
}