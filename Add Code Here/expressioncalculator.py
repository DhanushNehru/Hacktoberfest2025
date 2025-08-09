import math

def calculator():
    while True:
        expression = input("Enter a mathematical expression (or 'exit' to quit) using +,-,*,^,√: ")

        #breaks if inputted exit
        if expression.lower() == 'exit':
            break

        #turns generic ^ to ** for code
        expression = expression.replace('^', '**')
        #turns the square root symbol to something that can be evaluated in python
        expression = expression.replace('√', 'math.sqrt(') + ')'

        #evaluates the given input
        try:
            result = eval(expression)
            print(f"The result is: {result}")
        #gives an error if there is an unknown input
        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    calculator()
