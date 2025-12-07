#include <bits/stdc++.h>
using namespace std;

enum Color { RED, BLACK };

template<typename T>
struct RBNode {
    T key;
    Color color;
    RBNode *left, *right, *parent;
    RBNode(const T& k, Color c, RBNode* nil)
        : key(k), color(c), left(nil), right(nil), parent(nil) {}
};

template<typename T>
class RBTree {
public:
    RBTree();
    ~RBTree();

    void insert(const T& key);
    bool remove(const T& key);
    RBNode<T>* search(const T& key) const;
    void inorder() const;
    bool empty() const { return root == nil; }

private:
    RBNode<T>* root;
    RBNode<T>* nil; // sentinel

    // core helpers
    void leftRotate(RBNode<T>* x);
    void rightRotate(RBNode<T>* y);
    void insertFixup(RBNode<T>* z);
    void deleteFixup(RBNode<T>* x);
    void transplant(RBNode<T>* u, RBNode<T>* v);

    RBNode<T>* minimum(RBNode<T>* x) const;
    void inorderHelper(RBNode<T>* x) const;
    void clear(RBNode<T>* x);

    // utility
    void printNode(RBNode<T>* x) const;
};

template<typename T>
RBTree<T>::RBTree() {
    nil = new RBNode<T>(T(), BLACK, nullptr);
    nil->left = nil->right = nil->parent = nil;
    root = nil;
}

template<typename T>
RBTree<T>::~RBTree() {
    clear(root);
    delete nil;
}

template<typename T>
void RBTree<T>::clear(RBNode<T>* x) {
    if (x == nil) return;
    clear(x->left);
    clear(x->right);
    delete x;
}

// Left rotate around x (assumes x->right != nil)
template<typename T>
void RBTree<T>::leftRotate(RBNode<T>* x) {
    RBNode<T>* y = x->right;
    x->right = y->left;
    if (y->left != nil) y->left->parent = x;
    y->parent = x->parent;
    if (x->parent == nil) root = y;
    else if (x == x->parent->left) x->parent->left = y;
    else x->parent->right = y;
    y->left = x;
    x->parent = y;
}

// Right rotate around y (assumes y->left != nil)
template<typename T>
void RBTree<T>::rightRotate(RBNode<T>* y) {
    RBNode<T>* x = y->left;
    y->left = x->right;
    if (x->right != nil) x->right->parent = y;
    x->parent = y->parent;
    if (y->parent == nil) root = x;
    else if (y == y->parent->right) y->parent->right = x;
    else y->parent->left = x;
    x->right = y;
    y->parent = x;
}

template<typename T>
void RBTree<T>::insert(const T& key) {
    // Standard BST insert
    RBNode<T>* z = new RBNode<T>(key, RED, nil);
    RBNode<T>* y = nil;
    RBNode<T>* x = root;
    while (x != nil) {
        y = x;
        if (z->key < x->key) x = x->left;
        else x = x->right;
    }
    z->parent = y;
    if (y == nil) root = z;
    else if (z->key < y->key) y->left = z;
    else y->right = z;

    z->left = nil;
    z->right = nil;
    z->color = RED;

    insertFixup(z);
}

template<typename T>
void RBTree<T>::insertFixup(RBNode<T>* z) {
    // Fix red-black properties after insert
    while (z->parent->color == RED) {
        if (z->parent == z->parent->parent->left) {
            RBNode<T>* y = z->parent->parent->right; // uncle
            if (y->color == RED) {
                // case 1
                z->parent->color = BLACK;
                y->color = BLACK;
                z->parent->parent->color = RED;
                z = z->parent->parent;
            } else {
                if (z == z->parent->right) {
                    // case 2
                    z = z->parent;
                    leftRotate(z);
                }
                // case 3
                z->parent->color = BLACK;
                z->parent->parent->color = RED;
                rightRotate(z->parent->parent);
            }
        } else {
            // mirror image of above
            RBNode<T>* y = z->parent->parent->left;
            if (y->color == RED) {
                z->parent->color = BLACK;
                y->color = BLACK;
                z->parent->parent->color = RED;
                z = z->parent->parent;
            } else {
                if (z == z->parent->left) {
                    z = z->parent;
                    rightRotate(z);
                }
                z->parent->color = BLACK;
                z->parent->parent->color = RED;
                leftRotate(z->parent->parent);
            }
        }
    }
    root->color = BLACK;
}

template<typename T>
RBNode<T>* RBTree<T>::search(const T& key) const {
    RBNode<T>* x = root;
    while (x != nil && key != x->key) {
        if (key < x->key) x = x->left;
        else x = x->right;
    }
    return (x == nil) ? nullptr : x;
}

template<typename T>
RBNode<T>* RBTree<T>::minimum(RBNode<T>* x) const {
    while (x->left != nil) x = x->left;
    return x;
}

template<typename T>
void RBTree<T>::transplant(RBNode<T>* u, RBNode<T>* v) {
    if (u->parent == nil) root = v;
    else if (u == u->parent->left) u->parent->left = v;
    else u->parent->right = v;
    v->parent = u->parent;
}

// Delete key; return true if deleted
template<typename T>
bool RBTree<T>::remove(const T& key) {
    RBNode<T>* z = root;
    while (z != nil && z->key != key) {
        if (key < z->key) z = z->left;
        else z = z->right;
    }
    if (z == nil) return false; // not found

    RBNode<T>* y = z;
    Color y_original_color = y->color;
    RBNode<T>* x = nullptr;

    if (z->left == nil) {
        x = z->right;
        transplant(z, z->right);
    } else if (z->right == nil) {
        x = z->left;
        transplant(z, z->left);
    } else {
        y = minimum(z->right);
        y_original_color = y->color;
        x = y->right;
        if (y->parent == z) {
            x->parent = y;
        } else {
            transplant(y, y->right);
            y->right = z->right;
            y->right->parent = y;
        }
        transplant(z, y);
        y->left = z->left;
        y->left->parent = y;
        y->color = z->color;
    }

    delete z;
    if (y_original_color == BLACK) {
        deleteFixup(x);
    }
    return true;
}

template<typename T>
void RBTree<T>::deleteFixup(RBNode<T>* x) {
    while (x != root && x->color == BLACK) {
        if (x == x->parent->left) {
            RBNode<T>* w = x->parent->right;
            if (w->color == RED) {
                // case 1
                w->color = BLACK;
                x->parent->color = RED;
                leftRotate(x->parent);
                w = x->parent->right;
            }
            if (w->left->color == BLACK && w->right->color == BLACK) {
                // case 2
                w->color = RED;
                x = x->parent;
            } else {
                if (w->right->color == BLACK) {
                    // case 3
                    w->left->color = BLACK;
                    w->color = RED;
                    rightRotate(w);
                    w = x->parent->right;
                }
                // case 4
                w->color = x->parent->color;
                x->parent->color = BLACK;
                w->right->color = BLACK;
                leftRotate(x->parent);
                x = root;
            }
        } else {
            // mirror cases
            RBNode<T>* w = x->parent->left;
            if (w->color == RED) {
                w->color = BLACK;
                x->parent->color = RED;
                rightRotate(x->parent);
                w = x->parent->left;
            }
            if (w->right->color == BLACK && w->left->color == BLACK) {
                w->color = RED;
                x = x->parent;
            } else {
                if (w->left->color == BLACK) {
                    w->right->color = BLACK;
                    w->color = RED;
                    leftRotate(w);
                    w = x->parent->left;
                }
                w->color = x->parent->color;
                x->parent->color = BLACK;
                w->left->color = BLACK;
                rightRotate(x->parent);
                x = root;
            }
        }
    }
    x->color = BLACK;
}

template<typename T>
void RBTree<T>::inorderHelper(RBNode<T>* x) const {
    if (x == nil) return;
    inorderHelper(x->left);
    printNode(x);
    inorderHelper(x->right);
}

template<typename T>
void RBTree<T>::inorder() const {
    inorderHelper(root);
    cout << '\n';
}

template<typename T>
void RBTree<T>::printNode(RBNode<T>* x) const {
    cout << x->key << (x->color == RED ? "(R) " : "(B) ");
}

// Demo
int main() {
    RBTree<int> tree;

    vector<int> vals = {10, 20, 30, 15, 25, 5, 1, 8, 12, 18};
    cout << "Inserting: ";
    for (int v : vals) cout << v << ' ';
    cout << "\n";

    for (int v : vals) tree.insert(v);

    cout << "Inorder (key(color)) after inserts:\n";
    tree.inorder();

    cout << "Search 15: ";
    auto node = tree.search(15);
    if (node) cout << "found " << node->key << '\n';
    else cout << "not found\n";

    cout << "Deleting 20, 10, 30\n";
    tree.remove(20);
    tree.remove(10);
    tree.remove(30);

    cout << "Inorder after deletes:\n";
    tree.inorder();

    return 0;
}
