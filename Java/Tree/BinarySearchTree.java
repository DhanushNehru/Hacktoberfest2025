// package Tree;

/**
 * A complete and self-contained implementation of a Binary Search Tree (BST) in Java.
 * This file includes the Node structure, the BST class with core operations,
 * and a main method to demonstrate its functionality.
 */
public class BinarySearchTree {

    /**
     * Inner class representing a node in the BST.
     * Each node holds an integer value and references to its left and right children.
     */
    static class Node {
        int data;
        Node left;
        Node right;

        // Constructor to create a new node
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // The root of the Binary Search Tree. It is null for an empty tree.
    private Node root;

    // Public constructor to create an empty BST.
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Public method to insert a new value into the BST.
     * It calls a private recursive helper method to perform the insertion.
     * @param data The integer value to be inserted.
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    /**
     * A recursive helper method to insert a new key in the BST.
     * It returns the new root of the subtree.
     * @param current The current node in the recursion.
     * @param data The data to insert.
     * @return The node where the new value is inserted.
     */
    private Node insertRecursive(Node current, int data) {
        // If the tree or subtree is empty, create a new node and return it.
        if (current == null) {
            return new Node(data);
        }

        // Otherwise, recur down the tree.
        if (data < current.data) {
            // If the data to insert is smaller, go to the left subtree.
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            // If the data to insert is larger, go to the right subtree.
            current.right = insertRecursive(current.right, data);
        }

        // Return the (unchanged) node pointer.
        // This is important for connecting the nodes up the recursion chain.
        return current;
    }

    /**
     * Public method to search for a value in the BST.
     * @param data The value to search for.
     * @return true if the value is found, false otherwise.
     */
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    /**
     * A recursive helper method to search for a key in the BST.
     * @param current The current node in the recursion.
     * @param data The data to search for.
     * @return true if found, false otherwise.
     */
    private boolean searchRecursive(Node current, int data) {
        // Base case: If the node is null, the value is not in the tree.
        if (current == null) {
            return false;
        }
        // If the data is found at the current node.
        if (data == current.data) {
            return true;
        }
        // If the data is smaller, search in the left subtree.
        if (data < current.data) {
            return searchRecursive(current.left, data);
        }
        // Otherwise, search in the right subtree.
        return searchRecursive(current.right, data);
    }

    // --- Tree Traversal Methods ---

    /**
     * Performs an in-order traversal of the BST.
     * (Left Subtree -> Root -> Right Subtree)
     * This traversal visits nodes in ascending order.
     */
    public void inOrderTraversal() {
        System.out.print("In-order Traversal: ");
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.data + " ");
            inOrderRecursive(node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the BST.
     * (Root -> Left Subtree -> Right Subtree)
     * Useful for creating a copy of the tree.
     */
    public void preOrderTraversal() {
        System.out.print("Pre-order Traversal: ");
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    /**
     * Performs a post-order traversal of the BST.
     * (Left Subtree -> Right Subtree -> Root)
     * Useful for deleting nodes from the tree.
     */
    public void postOrderTraversal() {
        System.out.print("Post-order Traversal: ");
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }


    /**
     * The main method to demonstrate the functionality of the BinarySearchTree.
     */
    public static void main(String[] args) {
        System.out.println("--- Binary Search Tree Demonstration ---");
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes into the tree
        System.out.println("Inserting values: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Perform and print traversals
        System.out.println("\n--- Tree Traversals ---");
        bst.inOrderTraversal();   // Expected: 20 30 40 50 60 70 80
        bst.preOrderTraversal();  // Expected: 50 30 20 40 70 60 80
        bst.postOrderTraversal(); // Expected: 20 40 30 60 80 70 50

        // Search for values in the tree
        System.out.println("\n--- Searching for Values ---");
        System.out.println("Is 40 in the tree? " + bst.search(40)); // Expected: true
        System.out.println("Is 90 in the tree? " + bst.search(90)); // Expected: false
    }
}

