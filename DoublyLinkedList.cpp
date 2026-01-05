#include <bits/stdc++.h>
using namespace std;
class Node{
    public:
    int data;
    Node* next;
    Node* back;
    public:
    Node(int data1, Node* next1, Node* back1){
        data = data1;
        next = next1;
        back = back1;
    }
    public:
    Node(int data1){
        data = data1;
        next = nullptr;
        back = nullptr;
    }
};
Node* convertArr2DLL(vector<int> arr){
    Node* head = new Node(arr[0]);
    Node* prev = head;
    for(int i=1 ; i<arr.size() ; i++){
        Node* temp = new Node(arr[i],nullptr,prev);
        prev->next=temp;
        prev = temp;
    }
    return head;
}
void print(Node* head){
    while(head != NULL){
        cout << head->data << " ";
        head = head->next;
    }
}
Node* deleteHead(Node* head){
    if(head==NULL || head->next==NULL) return NULL;
    Node* prev = head;
    head = head->next;
    head->back = nullptr;
    prev->next = nullptr;
    delete(prev);
    return head;
}
Node* deleteTail(Node* head){
    if(head==NULL || head->next==NULL) return NULL;
    Node* tail = head;
    while(tail->next != NULL){
        tail = tail->next;
    }
    Node* prev = tail->back;
    prev->next=nullptr;
    tail->back=nullptr;
    delete(tail);
    return head;
}
Node* removeKthelement(Node* head , int k){
    if(head==NULL){
        return NULL;
    }
    int cnt=0;
    Node* temp = head;
    while(temp!=NULL){
        cnt++;
        if(cnt==k) break;
        temp = temp->next;
    }
    Node* prev = temp->back;
    Node* front = temp->next;
    if(prev==NULL && front==NULL){
        return NULL;
    }
    else if(prev==NULL){
        return deleteHead(head);
    }
    else if(front==NULL){
        return deleteTail(head);
    }
    prev->next = front;
    front->back = prev;
    temp->next = temp->back = nullptr;
    delete(temp);
    return head;
}
void deleteNode(Node* temp){
    Node* prev = temp->back;
    Node* front = temp->next;
    if(front==NULL){
        prev->next = nullptr;
        temp->back = nullptr;
        delete(temp);
        return;
    }
    prev->next = front;
    front->back = prev;
    temp->next = temp->back = nullptr;
    delete(temp);
}
Node* insertBeforeHead(Node* head, int val){
    Node* newHead = new Node(val,head,nullptr);
    head->back = newHead;
    return newHead;
}
Node* insertBeforeTail(Node* head, int val){
    if(head==NULL){
        return new Node(val);
    }
    if(head->next==NULL){
        return insertBeforeHead(head , val);
    }
    Node* tail = head;
    while(tail->next != NULL){
        tail = tail->next;
    }
    Node* prev = tail->back;
    Node* newNode = new Node(val,tail,prev);
    prev->next= newNode;
    tail->back = newNode;
    return head;
}
Node* insertBeforeKthElement(Node* head, int val , int k){
    if(k==1){
        return insertBeforeHead(head , val);
    }
    int cnt=0;
    Node* temp = head;
    while(temp != NULL){
        cnt++;
        if(cnt==k) break;
        temp = temp->next;
    }
    Node* prev = temp->back;
    Node* newNode = new Node(val,temp,prev);
    prev->next = newNode;
    temp->back = newNode;
    return head;
}
void insertBeforeNode(Node* node , int val){
    Node* prev = node->back;
    Node* newNode = new Node(val,node,prev);
    prev->next = newNode;
    node->back = newNode;
}
Node* insertAtTail(Node* head, int val){
    if(head==NULL) return new Node(val);
    Node* tail = head;
    while(tail->next != NULL){
        tail = tail->next;
    }
    Node* newNode = new Node(val,nullptr,tail);
    tail->next = newNode;
    return head;
}
int main(){
    vector<int> arr = {12,5,8,7};
    Node* head = convertArr2DLL(arr);
    head = insertAtTail(head , 10);
    print(head);
}
