# In this program we will create a Binary Search Tree (BST)

class Node:
    def __init__(self, data):
        self.val = data
        self.left = None
        self.right = None

def insert_into_bst(root, value):
    if root is None:
        return Node(value)
    
    if value > root.val:
        root.right = insert_into_bst(root.right, value)
    else:
        root.left = insert_into_bst(root.left, value)
    
    return root

def take_input():
    root = None
    data = int(input("Enter data (-1 to stop): "))
    
    while data != -1:
        root = insert_into_bst(root, data)
        data = int(input())
    
    return root

root = take_input()
