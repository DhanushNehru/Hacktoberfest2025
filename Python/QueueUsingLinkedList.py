# Python program to implement the queue data structure using linked lists.

# Node class represents each element in the queue
class Node:
    def __init__(self, data: int):
        self.data = data  # Store the value of the node
        self.next = None  # Pointer to the next node


# Queue class implements queue operations using a linked list
class Queue:
    def __init__(self):
        # Pointers to the front and rear of the queue
        self.front: Node | None = None
        self.rear: Node | None = None

    # Function to check if the queue is empty
    def is_empty(self) -> bool:
        # If front is None, the queue is empty
        return self.front is None

    # Function to add an element to the queue
    def enqueue(self, data: int) -> None:
        # Create a new node
        new_node = Node(data)

        # If the queue is empty, the new node is both front and rear
        if self.rear is None:
            self.front = self.rear = new_node
            return

        # Add the new node at the end and update rear
        self.rear.next = new_node
        self.rear = new_node

    # Function to remove an element from the queue
    def dequeue(self) -> int | None:
        # If the queue is empty, return None
        if self.is_empty():
            print("Queue Underflow")
            return None

        # Get the data from front node
        removed_data = self.front.data

        # Move front pointer to the next node
        self.front = self.front.next

        # If front becomes None, then the queue is empty, so rear also becomes None
        if self.front is None:
            self.rear = None

        # Return the removed data
        return removed_data

    # Function to get the front element of the queue
    def get_front(self) -> int | None:
        if self.is_empty():
            print("Queue is empty")
            return None
        return self.front.data

    # Function to get the rear element of the queue
    def get_rear(self) -> int | None:
        if self.is_empty():
            print("Queue is empty")
            return None
        return self.rear.data

    # Function to display the queue elements
    def __str__(self) -> str:
        values = []
        current = self.front
        while current:
            values.append(str(current.data))
            current = current.next
        return " <- ".join(values)


# Driver code
if __name__ == "__main__":
    q = Queue()

    # Enqueue elements into the queue
    q.enqueue(10)
    q.enqueue(20)

    # Display the queue, front, and rear elements
    print("Queue:", q)
    print("Queue Front:", q.get_front())
    print("Queue Rear:", q.get_rear())

    # Dequeue elements from the queue
    print("Dequeued:", q.dequeue())
    print("Dequeued:", q.dequeue())

    # Display the queue after dequeue operations
    print("Queue:", q)

    # Enqueue more elements
    q.enqueue(30)
    q.enqueue(40)
    q.enqueue(50)
    print("Queue:", q)

    # Dequeue an element
    print("Dequeued:", q.dequeue())

    # Display the queue, front, and rear elements
    print("Queue:", q)
    print("Queue Front:", q.get_front())
    print("Queue Rear:", q.get_rear())

    