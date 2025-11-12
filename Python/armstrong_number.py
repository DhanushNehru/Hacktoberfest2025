def is_armstrong(number: int) -> bool:
    """Return True if number is an Armstrong number."""
    digits = str(number)
    power = len(digits)
    total = sum(int(digit) ** power for digit in digits)
    return total == number


if __name__ == "__main__":
    num = int(input("Enter a number: "))
    if is_armstrong(num):
        print("Armstrong number")
    else:
        print("Not Armstrong number")
