# Enhanced Calculator Program

def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    if y == 0:
        return "Error! Division by zero."
    else:
        return x / y

def exponentiate(x, y):
    return x ** y

def modulus(x, y):
    if y == 0:
        return "Error! Modulus by zero."
    else:
        return x % y

def get_number(prompt):
    while True:
        try:
            return float(input(prompt))
        except ValueError:
            print("Invalid input. Please enter a numeric value.")

def calculator():
    history = []
    operations = {
        '1': ("Add", add),
        '2': ("Subtract", subtract),
        '3': ("Multiply", multiply),
        '4': ("Divide", divide),
        '5': ("Exponentiate", exponentiate),
        '6': ("Modulus", modulus)
    }

    print("Enhanced Calculator")
    print("-------------------")

    while True:
        print("\nSelect operation:")
        for key, (name, _) in operations.items():
            print(f"{key}. {name}")
        print("7. View Calculation History")
        print("8. Clear Calculation History")
        print("9. Exit")

        choice = input("Enter choice (1-9): ")

        if choice in operations:
            num1 = get_number("Enter first number: ")
            num2 = get_number("Enter second number: ")

            operation_name, operation_func = operations[choice]
            result = operation_func(num1, num2)

            output = f"{operation_name} : {num1}, {num2} = {result}"
            print(output)

            # Save to history
            history.append(output)

        elif choice == '7':
            if history:
                print("\nCalculation History:")
                for record in history:
                    print(record)
            else:
                print("No calculations yet.")

        elif choice == '8':
            history.clear()
            print("Calculation history cleared.")

        elif choice == '9':
            print("Exiting the calculator. Goodbye!")
            break

        else:
            print("Invalid choice. Please select a valid option.")

if __name__ == "__main__":
    calculator()
