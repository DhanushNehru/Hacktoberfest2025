#include <iostream>
using namespace std;

typedef struct Node
{
    int data;
    Node *left;
    Node *right;
} Node;

Node *createNode(int data)
{
    Node *newNode = new Node();
    newNode->data = data;
    newNode->left = newNode->right = nullptr;
    return newNode;
}

Node *insertNode(Node *root, int data)
{
    if (root == nullptr)
    {
        return createNode(data);
    }

    if (data < root->data)
    {
        root->left = insertNode(root->left, data);
    }
    else if (data > root->data)
    {
        root->right = insertNode(root->right, data);
    }

    return root;
}

void postTrav(Node *root)
{
    if (root != nullptr)
    {
        postTrav(root->left);
        postTrav(root->right);
        cout << root->data << " ";
    }
}

void preTrav(Node *root)
{
    if (root != nullptr)
    {
        cout << root->data << " ";
        preTrav(root->left);
        preTrav(root->right);
    }
}

void inorderTrav(Node *root)
{
    if (root != nullptr)
    {
        inorderTrav(root->left);
        cout << root->data << " ";
        inorderTrav(root->right);
    }
}

Node* searchNode(Node *root, int key)
{
    if (root == nullptr || root->data == key)
    {
        return root;
    }

    if (root->data < key)
    {
        return searchNode(root->right, key);
    }

    return searchNode(root->left, key);
}

int main()
{
    Node *root = nullptr;
    int choice = 1;
    while (choice)
    {
        cout << "\nPress 1 to enter a node\nPress 2 to search a node\nPress 3 to get the inorder traversal\nPress 4 to get the preorder traversal\nPress 5 to get the postorder traversal\nPress 0 to exit" << endl;
        cin >> choice;
        int val;
        switch (choice)
        {
        case 0:
            break;
        case 1:
            cout << "Enter value : ";
            cin >> val;
            root = insertNode(root, val);
            break;
        case 2:
            cout << "Enter the value to be searched : ";
            cin >> val;

            if (searchNode(root, val))
                cout << "The value " << val << " is present in the tree";
            else
                cout << "The value " << val << " is not present in the tree";
            break;
        case 3:
            cout << "The inorder traversal of tree is : " << endl;
            inorderTrav(root);
            break;
        case 4:
            cout << "The preorder traversal of tree is : " << endl;
            preTrav(root);
            break;
        case 5:
            cout << "The postorder traversal of tree is : " << endl;
            postTrav(root);
            break;
        default:
            break;
        }
        if (!choice)
            break;
    }
    return 0;
}
