def collatz(n: int):
    sequence = [n]
    while n != 1:
        if n % 2 == 0:
            n //= 2
        else:
            n = 3 * n + 1
        sequence.append(n)
    return sequence

def print_collatz_table(seq):
    print("\nStep |  Value | Operation")
    print("-" * 30)
    for i in range(len(seq) - 1):
        curr, nxt = seq[i], seq[i + 1]
        if curr % 2 == 0:
            op = f"{curr} / 2 = {nxt}"
        else:
            op = f"3 ({curr}) + 1 = {nxt}"
        print(f"{i:>4} | {curr:>6} | {op}")
    print(f"{len(seq)-1:>4} | {seq[-1]:>6} | Reached 1")

def main():
    print("Welcome to the Collatz Exploration!")
    try:
        n = int(input("Enter a positive integer to begin: "))
        if n <= 0:
            print("Please enter a positive integer greater than zero.")
            return
    except ValueError:
        print("Invalid input! Please enter an integer.")
        return

    sequence = collatz(n)
    print_collatz_table(sequence)

    print("\nAfter", len(sequence)-1, "steps, the sequence reaches 1.")
    print("No matter how long the sequence seems, it always ends in the cycle 4 → 2 → 1.")
    print("Just ike life’s own ups and downs, the Collatz path may wander chaotically,")
    print("but it eventually settles into a simple, eternal rhythm — 4, 2, 1.\n")

if __name__ == "__main__":
    main()
