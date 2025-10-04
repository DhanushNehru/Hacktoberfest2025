#include <bits/stdc++.h>
using namespace std;

class Node{  // Definition of linked list
    public:
    int data;
    Node* next;
    public:
    Node (int data1 , Node* next1){  // Multiple constructors 
        data = data1;
        next = next1;
    }
    public:
    Node (int data1){  // Multiple constructors
        data = data1;
        next = nullptr;
    }
};

Node* convertArr2LL(vector<int> arr){  // This function converts a vector into linked list
    Node* head = new Node(arr[0]);
    Node* mover = head;
    for(int i=1 ; i<arr.size() ; i++){
        Node* temp = new Node(arr[i]);
        mover->next = temp;
        mover = temp;
    }
    return head;
}

void print(Node* head){  // This function prints the linked list
    Node* temp = head;
    while(temp != NULL){
        cout << temp->data << " ";
        temp = temp->next;
    }
    cout << endl;
}

Node* deleteGivenElement(Node* head, int val){  // This function deletes a given element
    if(head==NULL) return head;
    if(head->data==val){
        Node* temp = head;
        head = head->next;
        delete(temp);
        return head;
    }
    Node* temp = head;
    Node* prev = nullptr;
    while(temp!=NULL){
        if(temp->data==val){
            prev->next = temp->next;
            temp->next = NULL;
            delete(temp);
            break;
        }
        prev = temp;
        temp = temp->next;
    }
    return head;
}

Node* insertGivenElement(Node* head, int val, int pos){  // This function inserts a given element at desired location
    if(head==NULL){
        return new Node(val);
    }
    if(pos==0){
        Node* temp = new Node(val, head);
        head = temp;
        return head;
    }
    Node* temp = head;
    Node* prev = NULL;
    int cnt=0;
    while(temp != NULL){
        if(cnt==pos){
            Node* newNode = new Node(val,temp);
            prev->next = newNode;
            break;
        }
        cnt++;
        prev = temp;
        temp = temp->next;
    }
    if(pos>=cnt){
        Node* newNode = new Node(val, temp);
        prev->next = newNode;
    }
    return head;
}

int main(){
    vector <int> arr = {2,5,8,7};  // Sample vector
    Node* head = convertArr2LL(arr);
    print(head);
    head = deleteGivenElement(head,5);
    print(head);
    head = insertGivenElement(head, 10, 2);
    print(head);
    return 0;
}
