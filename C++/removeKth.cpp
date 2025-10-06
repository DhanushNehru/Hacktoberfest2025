//Remove every k-th node of the linked list
#include<iostream>
using namespace std;
class node
{
    public:
    int data;
    node* next;
};
node* remove_kth(node** href, int k)
{
    node* temp = *href;
    if(k<=0)
    {
        cout << "Invalid value of k" << endl;
        return NULL;
    }
    else if(k==1)
    {
        node* temp = *href;
        *href = temp -> next;
        delete(temp);
        return NULL;
    }

    int count=0;
    node* current = *href;
    node* prev = NULL;
    while(current!=NULL)
    {
        count++;
        if(count%k==0)
        {
            node* temp = current;
            current =  current -> next;
            if(prev!=NULL)
            {
                prev->next=current;
            }
            delete(temp);
        }
        else
        {
            prev = current;
            current = current -> next;
        }
    }
    return *href;
}
void add_last(node** href, int value)
{
    node* temp = new node;
    node* current = *href;
    temp -> data = value;
    temp -> next = NULL;

    if(*href==NULL)
    {
        *href=temp;
        return;
    }
    while(current->next!=NULL)
    {
        current = current->next;
    }
    current->next=temp;
}
void display(node* my_node)
{
    node * current = my_node;
    while (current!=NULL)
    {
        cout << current->data << " ";
        current = current -> next;
    }
}
int main()
{
    node* head = NULL;
    add_last(&head, 1);
    add_last(&head, 2);
    add_last(&head, 3);
    add_last(&head, 4);
    add_last(&head, 5);
    add_last(&head, 6);
    add_last(&head, 7);
    add_last(&head, 8);
    display(head);
    cout << endl;
    
    head = remove_kth(&head, 2);
    display(head);
    return 0;
}