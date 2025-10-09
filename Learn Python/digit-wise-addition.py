def add_two_numbers(num1, num2):
    result = 0
    carry = 0
    place = 1

    while num1 > 0 or num2 > 0 or carry > 0:
        # Extract last digits
        d1 = num1 % 10
        d2 = num2 % 10

        # Digit-wise addition
        s = d1 + d2 + carry
        carry = s // 10
        digit = s % 10

        # Build result
        result = result + digit * place
        place = place * 10

        # Remove last digits
        num1 = num1 // 10
        num2 = num2 // 10

    return result
