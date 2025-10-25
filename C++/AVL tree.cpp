#include <bits/stdc++.h>
#include <iostream>
using namespace std;

typedef struct TreeNode
{
    TreeNode *left;
    TreeNode *right;
    int key;
    TreeNode *par;
    int height;
} TreeNode;

void Updateheight(TreeNode *root)
{
    if (root != nullptr)
    {
        int val = 1;
        int lefti = 0;
        int righti = 0;
        if (root->left != nullptr)
            lefti = root->left->height + 1;

        if (root->right != nullptr)
            righti = root->right->height + 1;

        val = max(lefti, righti);
        root->height = val;
    }
}

TreeNode *LLR(TreeNode *root)
{
    TreeNode *temp = root->left;

    root->left = temp->right;

    if (temp->right != nullptr)
        temp->right->par = root;

    temp->right = root;
    temp->par = root->par;
    root->par = temp;

    if (temp->par != nullptr && root->key < temp->par->key)
    {
        temp->par->left = temp;
    }
    else
    {
        if (temp->par != nullptr)
            temp->par->right = temp;
    }

    root = temp;

    Updateheight(root->left);
    Updateheight(root->right);
    Updateheight(root);
    Updateheight(root->par);

    return root;
}

TreeNode *RRR(TreeNode *root)
{
    TreeNode *temp = root->right;

    root->right = temp->left;

    if (temp->left != nullptr)
        temp->left->par = root;

    temp->left = root;
    temp->par = root->par;
    root->par = temp;

    if (temp->par != nullptr && root->key < temp->par->key)
    {
        temp->par->left = temp;
    }
    else
    {
        if (temp->par != nullptr)
            temp->par->right = temp;
    }

    root = temp;

    Updateheight(root->left);
    Updateheight(root->right);
    Updateheight(root);
    Updateheight(root->par);

    return root;
}

TreeNode *LRR(TreeNode *root)
{
    root->left = RRR(root->left);
    return LLR(root);
}

TreeNode *RLR(TreeNode *root)
{
    root->right = LLR(root->right);
    return RRR(root);
}

TreeNode *insertNode(TreeNode *root, TreeNode *parent, int key)
{
    if (root == nullptr)
    {
        root = new TreeNode;
        root->height = 1;
        root->left = nullptr;
        root->right = nullptr;
        root->par = parent;
        root->key = key;
    }
    else if (root->key > key)
    {
        root->left = insertNode(root->left, root, key);

        int firstheight = 0;
        int secondheight = 0;

        if (root->left != nullptr)
            firstheight = root->left->height;

        if (root->right != nullptr)
            secondheight = root->right->height;

        if (abs(firstheight - secondheight) == 2)
        {
            if (root->left != nullptr && key < root->left->key)
            {
                root = LLR(root);
            }
            else
            {
                root = LRR(root);
            }
        }
    }
    else if (root->key < key)
    {
        root->right = insertNode(root->right, root, key);

        int firstheight = 0;
        int secondheight = 0;

        if (root->left != nullptr)
            firstheight = root->left->height;

        if (root->right != nullptr)
            secondheight = root->right->height;

        if (abs(firstheight - secondheight) == 2)
        {
            if (root->right != nullptr && key < root->right->key)
            {
                root = RLR(root);
            }
            else
            {
                root = RRR(root);
            }
        }
    }
    Updateheight(root);

    return root;
}
struct TreeNode *findMin(struct TreeNode *node)
{
    while (node->left != nullptr)
        node = node->left;
    return node;
}

struct TreeNode *deleteNode(struct TreeNode *root, int key)
{
    if (root == nullptr)
        return root;
    if (key < root->key)
    {
        root->left = deleteNode(root->left, key);
    }
    else if (key > root->key)
    {
        root->right = deleteNode(root->right, key);
    }
    else
    {
        if ((root->left == nullptr) || (root->right == nullptr))
        {
            TreeNode *temp = root->left ? root->left : root->right;

            if (temp == nullptr)
            {
                temp = root;
                root = nullptr;
            }
            else
            {
                *root = *temp;
            }
            delete temp;
        }
        else
        {
            TreeNode *temp = findMin(root->right);

            root->key = temp->key;

            root->right = deleteNode(root->right, temp->key);
        }
    }

    if (root == nullptr)
        return root;

    Updateheight(root);

    int balance = 0;
    if (root->left != nullptr)
        balance += root->left->height;
    if (root->right != nullptr)
        balance -= root->right->height;

    if (balance > 1 && key < root->left->key)
        return LLR(root);

    if (balance < -1 && key > root->right->key)
        return RRR(root);

    if (balance > 1 && key > root->left->key)
    {
        root->left = RRR(root->left);
        return LLR(root);
    }

    if (balance < -1 && key < root->right->key)
    {
        root->right = LLR(root->right);
        return RRR(root);
    }

    return root;
}

void inorderTrav(TreeNode *root)
{
    if (root == nullptr)
        return;

    if (root->left != nullptr)
    {
        inorderTrav(root->left);
    }

    cout << "Node: " << root->key << ", Parent Node: ";
    if (root->par != nullptr)
        cout << root->par->key << endl;
    else
        cout << "NULL" << endl;

    if (root->right != nullptr)
    {
        inorderTrav(root->right);
    }
}

bool searchNode(TreeNode *root, int val)
{
    if (root == nullptr)
    {
        return false;
    }

    if (root->key == val)
    {
        return true;
    }
    else if (val > root->key)
    {
        return searchNode(root->right, val);
    }
    else
    {
        return searchNode(root->left, val);
    }
}

int main()
{
    TreeNode *root = nullptr;
    int choice = 1;
    while (choice)
    {
        cout << "\nPress 1 to enter a node\nPress 2 to search a node\nPress 3 to delete a node\nPress 4 to get the inorder traversal\nPress 0 to exit" << endl;
        cin >> choice;
        int val;
        switch (choice)
        {
        case 0:
            break;
        case 1:
            cout << "Enter value : ";
            cin >> val;
            root = insertNode(root, nullptr, val);
            break;
        case 2:
            cout << "Enter the value to be searched : ";
            cin >> val;

            if (searchNode(root, val))
                cout << "The value " << val << " is present in the tree\n";
            else
                cout << "The value " << val << " is not present in the tree\n";
            break;
        case 3:
            cout << "Enter the value to be deleted : ";
            cin >> val;
            root = deleteNode(root, val);
            break;
        case 4:
            cout << "The inorder traversal of tree is : " << endl;
            inorderTrav(root);
            break;

        default:
            break;
        }
        if (!choice)
            break;
    }
    return 0;
}
