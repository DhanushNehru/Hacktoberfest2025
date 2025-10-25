#include <iostream>
using namespace std;

class BTreeNode
{
public:
    int *keys;
    int degree, n;
    BTreeNode **children;
    bool leaf;

    BTreeNode(int deg, bool lf)
    {
        degree = deg;
        leaf = lf;
        keys = new int[2 * degree - 1];
        children = new BTreeNode *[2 * degree];
        n = 0;
    }

    void insertNonFull(int k)
    {
        int i = n - 1;
        if (leaf)
        {
            while (i >= 0 && keys[i] > k)
                keys[i + 1] = keys[i--];
            keys[i + 1] = k;
            n++;
        }
        else
        {
            while (i >= 0 && keys[i] > k)
                i--;
            if (children[i + 1]->n == 2 * degree - 1)
            {
                splitChild(i + 1, children[i + 1]);
                if (keys[i + 1] < k)
                    i++;
            }
            children[i + 1]->insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode *y)
    {
        BTreeNode *z = new BTreeNode(y->degree, y->leaf);
        z->n = degree - 1;
        for (int j = 0; j < degree - 1; j++)
            z->keys[j] = y->keys[j + degree];
        if (!y->leaf)
            for (int j = 0; j < degree; j++)
                z->children[j] = y->children[j + degree];
        y->n = degree - 1;
        for (int j = n; j >= i + 1; j--)
            children[j + 1] = children[j];
        children[i + 1] = z;
        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];
        keys[i] = y->keys[degree - 1];
        n++;
    }

    void traverse()
    {
        int i;
        for (i = 0; i < n; i++)
        {
            if (!leaf)
                children[i]->traverse();
            cout << keys[i] << " ";
        }
        if (!leaf)
            children[i]->traverse();
    }

    BTreeNode *search(int k)
    {
        int i = 0;
        while (i < n && k > keys[i])
            i++;
        if (keys[i] == k)
            return this;
        if (leaf)
            return nullptr;
        return children[i]->search(k);
    }

    int findKey(int k)
    {
        int index = 0;
        while (index < n && keys[index] < k)
            ++index;
        return index;
    }

    void remove(int k)
    {
        int index = findKey(k);
        if (index < n && keys[index] == k)
        {
            if (leaf)
                removeFromLeaf(index);
            else
                removeFromNonLeaf(index);
        }
        else
        {
            if (leaf)
                return;
            bool flag = (index == n);
            if (children[index]->n < degree)
                fill(index);
            if (flag && index > n)
                children[index - 1]->remove(k);
            else
                children[index]->remove(k);
        }
    }

    void removeFromLeaf(int index)
    {
        for (int i = index + 1; i < n; ++i)
            keys[i - 1] = keys[i];
        n--;
    }

    void removeFromNonLeaf(int index)
    {
        int k = keys[index];
        if (children[index]->n >= degree)
        {
            int pred = getPred(index);
            keys[index] = pred;
            children[index]->remove(pred);
        }
        else if (children[index + 1]->n >= degree)
        {
            int succ = getSucc(index);
            keys[index] = succ;
            children[index + 1]->remove(succ);
        }
        else
        {
            merge(index);
            children[index]->remove(k);
        }
    }

    int getPred(int index)
    {
        BTreeNode *cur = children[index];
        while (!cur->leaf)
            cur = cur->children[cur->n];
        return cur->keys[cur->n - 1];
    }

    int getSucc(int index)
    {
        BTreeNode *cur = children[index + 1];
        while (!cur->leaf)
            cur = cur->children[0];
        return cur->keys[0];
    }

    void fill(int index)
    {
        if (index != 0 && children[index - 1]->n >= degree)
            borrowFromPrev(index);
        else if (index != n && children[index + 1]->n >= degree)
            borrowFromNext(index);
        else
        {
            if (index != n)
                merge(index);
            else
                merge(index - 1);
        }
    }

    void borrowFromPrev(int index)
    {
        BTreeNode *child = children[index];
        BTreeNode *sibling = children[index - 1];
        for (int i = child->n - 1; i >= 0; --i)
            child->keys[i + 1] = child->keys[i];
        if (!child->leaf)
        {
            for (int i = child->n; i >= 0; --i)
                child->children[i + 1] = child->children[i];
        }
        child->keys[0] = keys[index - 1];
        if (!child->leaf)
            child->children[0] = sibling->children[sibling->n];
        keys[index - 1] = sibling->keys[sibling->n - 1];
        child->n += 1;
        sibling->n -= 1;
    }

    void borrowFromNext(int index)
    {
        BTreeNode *child = children[index];
        BTreeNode *sibling = children[index + 1];
        child->keys[child->n] = keys[index];
        if (!child->leaf)
            child->children[child->n + 1] = sibling->children[0];
        keys[index] = sibling->keys[0];
        for (int i = 1; i < sibling->n; ++i)
            sibling->keys[i - 1] = sibling->keys[i];
        if (!sibling->leaf)
        {
            for (int i = 1; i <= sibling->n; ++i)
                sibling->children[i - 1] = sibling->children[i];
        }
        child->n += 1;
        sibling->n -= 1;
    }

    void merge(int index)
    {
        BTreeNode *child = children[index];
        BTreeNode *sibling = children[index + 1];
        child->keys[degree - 1] = keys[index];
        for (int i = 0; i < sibling->n; ++i)
            child->keys[i + degree] = sibling->keys[i];
        if (!child->leaf)
        {
            for (int i = 0; i <= sibling->n; ++i)
                child->children[i + degree] = sibling->children[i];
        }
        for (int i = index + 1; i < n; ++i)
            keys[i - 1] = keys[i];
        for (int i = index + 2; i <= n; ++i)
            children[i - 1] = children[i];
        child->n += sibling->n + 1;
        n--;
        delete sibling;
    }
};

class BTree
{
    BTreeNode *root;
    int degree;

public:
    BTree(int deg) : root(nullptr), degree(deg) {}
    void traverse()
    {
        if (root)
            root->traverse();
    }
    BTreeNode *search(int k) { return root ? root->search(k) : nullptr; }

    void insert(int k)
    {
        if (!root)
        {
            root = new BTreeNode(degree, true);
            root->keys[0] = k;
            root->n = 1;
        }
        else
        {
            if (root->n == 2 * degree - 1)
            {
                BTreeNode *s = new BTreeNode(degree, false);
                s->children[0] = root;
                s->splitChild(0, root);
                int i = 0;
                if (s->keys[0] < k)
                    i++;
                s->children[i]->insertNonFull(k);
                root = s;
            }
            else
                root->insertNonFull(k);
        }
    }

    void remove(int k)
    {
        if (!root)
            return;
        root->remove(k);
        if (root->n == 0)
        {
            BTreeNode *tmp = root;
            if (root->leaf)
                root = nullptr;
            else
                root = root->children[0];
            delete tmp;
        }
    }
};

int main()
{
    BTree t(3);
    int keys[] = {6, 17, 4, 12, 19, 22, 2, 3, 5, 10, 16, 18, 20, 25};
    for (int key : keys)
        t.insert(key);
    cout << "Traversal of the constructed tree: ";
    t.traverse();
    cout << endl;

    t.remove(16);
    cout << "Traversal after removing 16: ";
    t.traverse();
    cout << endl;

    t.remove(20);
    cout << "Traversal after removing 20: ";
    t.traverse();
    cout << endl;

    t.remove(4);
    cout << "Traversal after removing 4: ";
    t.traverse();
    cout << endl;

    t.remove(6);
    cout << "Traversal after removing 6: ";
    t.traverse();
    cout << endl;
    return 0;
}
