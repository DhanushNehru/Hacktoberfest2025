#include <iostream>
using namespace std;

// A Splay Tree node
struct Node {
    int data;
    Node* left;
    Node* right;
    
    Node(int value) {
        data = value;
        left = right = nullptr;
    }
};

// Utility function to perform a right rotation
Node* rightRotate(Node* x) {
    Node* y = x->left;
    x->left = y->right;
    y->right = x;
    return y;
}

// Utility function to perform a left rotation
Node* leftRotate(Node* x) {
    Node* y = x->right;
    x->right = y->left;
    y->left = x;
    return y;
}

// Splay operation to move a node with key to the root of the tree
Node* splay(Node* root, int key) {
    if (root == nullptr || root->data == key) {
        return root;
    }

    // Key lies in left subtree
    if (key < root->data) {
        if (root->left == nullptr) return root;

        // Zig-Zig (Left Left)
        if (key < root->left->data) {
            root->left->left = splay(root->left->left, key);
            root = rightRotate(root);
        }
        // Zig-Zag (Left Right)
        else if (key > root->left->data) {
            root->left->right = splay(root->left->right, key);
            if (root->left->right != nullptr) {
                root->left = leftRotate(root->left);
            }
        }

        return (root->left == nullptr) ? root : rightRotate(root);
    } 
    // Key lies in right subtree
    else {
        if (root->right == nullptr) return root;

        // Zag-Zag (Right Right)
        if (key > root->right->data) {
            root->right->right = splay(root->right->right, key);
            root = leftRotate(root);
        }
        // Zag-Zig (Right Left)
        else if (key < root->right->data) {
            root->right->left = splay(root->right->left, key);
            if (root->right->left != nullptr) {
                root->right = rightRotate(root->right);
            }
        }

        return (root->right == nullptr) ? root : leftRotate(root);
    }
}

// Function to insert a new node with the given key
Node* insert(Node* root, int key) {
    if (root == nullptr) return new Node(key);

    root = splay(root, key);

    // If key is already present, return the root
    if (root->data == key) return root;

    // Create a new node
    Node* newNode = new Node(key);

    // If key is less than root, make root the right child of the new node
    if (key < root->data) {
        newNode->right = root;
        newNode->left = root->left;
        root->left = nullptr;
    }
    // If key is greater than root, make root the left child of the new node
    else {
        newNode->left = root;
        newNode->right = root->right;
        root->right = nullptr;
    }

    return newNode;
}

// Function to search for a node with the given key
Node* search(Node* root, int key) {
    return splay(root, key);
}

// Function to print the tree (Inorder Traversal)
void inorder(Node* root) {
    if (root == nullptr) return;
    inorder(root->left);
    cout << root->data << " ";
    inorder(root->right);
}

int main() {
    Node* root = nullptr;

    // Insert elements into the Splay Tree
    root = insert(root, 10);
    root = insert(root, 20);
    root = insert(root, 30);
    root = insert(root, 5);
    root = insert(root, 15);

    // Search for a value (this will also splay the node to the root)
    root = search(root, 15);

    cout << "Inorder Traversal after search for 15: ";
    inorder(root);  // Should show 5 10 15 20 30
    cout << endl;

    root = insert(root, 25);  // Inserting after splay operation
    cout << "Inorder Traversal after inserting 25: ";
    inorder(root);  // Should show 5 10 15 20 25 30
    cout << endl;

    return 0;
}
