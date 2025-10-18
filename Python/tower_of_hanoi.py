def tower_of_hanoi(n, source, auxiliary, destination):
    """
    Solves the Tower of Hanoi puzzle recursively.

    Args:
        n (int): The number of disks to move.
        source (str): The name of the source peg.
        auxiliary (str): The name of the auxiliary (temporary) peg.
        destination (str): The name of the destination peg.
    """
    if n == 1:
        # Base case: If there's only one disk, move it directly
        print(f"Move disk 1 from {source} to {destination}")
        return

    # Step 1: Move n-1 disks from source to auxiliary, using destination as temporary
    tower_of_hanoi(n - 1, source, destination, auxiliary)

    # Step 2: Move the nth disk from source to destination
    print(f"Move disk {n} from {source} to {destination}")

    # Step 3: Move the n-1 disks from auxiliary to destination, using source as temporary
    tower_of_hanoi(n - 1, auxiliary, source, destination)

# --- Main part to play the game ---
if __name__ == "__main__":
    print("Welcome to the Tower of Hanoi!")

    while True:
        try:
            num_disks = int(input("Enter the number of disks (e.g., 3 for a standard game): "))
            if num_disks <= 0:
                print("Please enter a positive number of disks.")
            else:
                break
        except ValueError:
            print("Invalid input. Please enter an integer.")

    print(f"\nSolving Tower of Hanoi with {num_disks} disks:")
    tower_of_hanoi(num_disks, 'A', 'B', 'C')
    print("\nTower of Hanoi puzzle solved!")

    # For 3 disks, it takes 2^3 - 1 = 7 moves.
    # For n disks, it takes 2^n - 1 moves.
    print(f"Total theoretical moves: {2**num_disks - 1}")
