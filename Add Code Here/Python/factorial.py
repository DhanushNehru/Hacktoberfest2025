# Simple Python program to calculate factorial and Fibonacci sequence
# This is a basic algorithm example for Hacktoberfest contribution

def factorial(n):
    """
    Calculate the factorial of a non-negative integer n.
    Factorial of n (n!) is the product of all positive integers less than or equal to n.
    For example, 5! = 5 * 4 * 3 * 2 * 1 = 120
    """
    if n < 0:
        return "Factorial is not defined for negative numbers"
    result = 1
    for i in range(1, n + 1):
        result *= i
    return result

def fibonacci(n):
    """
    Generate the Fibonacci sequence up to the nth term.
    Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, ...
    Returns a list of the first n Fibonacci numbers.
    """
    if n <= 0:
        return []
    elif n == 1:
        return [0]
    elif n == 2:
        return [0, 1]
    seq = [0, 1]
    for i in range(2, n):
        seq.append(seq[i-1] + seq[i-2])
    return seq

# Main function to get user input and display result
if __name__ == "__main__":
    print("Choose an option:")
    print("1. Calculate Factorial")
    print("2. Generate Fibonacci Sequence")
    choice = input("Enter 1 or 2: ")

    if choice == "1":
        try:
            num = int(input("Enter a non-negative integer for factorial: "))
            print(f"Factorial of {num} is {factorial(num)}")
        except ValueError:
            print("Please enter a valid integer.")
    elif choice == "2":
        try:
            num = int(input("Enter the number of Fibonacci terms: "))
            print(f"Fibonacci sequence: {fibonacci(num)}")
        except ValueError:
            print("Please enter a valid integer.")
    else:
        print("Invalid choice.")