#!/usr/bin/env python3
from colorama import Fore, Style, init
import argparse

init(autoreset=True)

BANNER = r"""
           ░██ ░██                           ░██                       ░██               ░██                        
           ░██                               ░██                       ░██               ░██                        
 ░███████  ░██ ░██      ░███████   ░██████   ░██  ░███████  ░██    ░██ ░██  ░██████   ░████████  ░███████  ░██░████ 
░██    ░██ ░██ ░██     ░██    ░██       ░██  ░██ ░██    ░██ ░██    ░██ ░██       ░██     ░██    ░██    ░██ ░███     
░██        ░██ ░██     ░██         ░███████  ░██ ░██        ░██    ░██ ░██  ░███████     ░██    ░██    ░██ ░██      
░██    ░██ ░██ ░██     ░██    ░██ ░██   ░██  ░██ ░██    ░██ ░██   ░███ ░██ ░██   ░██     ░██    ░██    ░██ ░██      
 ░███████  ░██ ░██      ░███████   ░█████░██ ░██  ░███████   ░█████░██ ░██  ░█████░██     ░████  ░███████  ░██      
"""

def show_banner():
    print(Fore.MAGENTA + BANNER)
    print(Fore.RED + "=" * 100)
    print(Fore.GREEN + "                           Cyber Multitool - Clean Edition")
    print(Fore.RED + "=" * 100)

def calc(args):
    if args.operator == "+":
        result = args.num1 + args.num2
    elif args.operator == "-":
        result = args.num1 - args.num2
    elif args.operator == "*":
        result = args.num1 * args.num2
    elif args.operator == "/":
        if args.num2 == 0:
            print(Fore.RED + "Error: Division by zero.")
            return
        result = args.num1 / args.num2
    elif args.operator == "^":
        result = args.num1 ** args.num2
    elif args.operator == "%":
        if args.num2 == 0:
            print(Fore.RED + "Undefined!")
            return
        result = args.num1 % args.num2
    else:
        print(Fore.RED + "Invalid operator!")
        return
    print(Fore.CYAN + f"Result: {result}")

def greet(args):
    print(Fore.BLUE + f"Hello, {args.name}! Welcome to My Cyber Multitool.")

def menu():
    show_banner()  # Show banner only once when menu starts
    while True:
        print(Fore.YELLOW + "[1] Calculator")
        print(Fore.YELLOW + "[2] Greet User")
        print(Fore.RED + "[0] Exit")
        choice = input(Fore.CYAN + "Select an option: ")

        if choice == "1":
            try:
                num1 = float(input("Enter the first number: "))
                op = input("Enter an operator (+ - * / ^ %): ")
                if op not in ["+", "-", "*", "/", "^", "%"]:
                    print(Fore.RED + "Invalid operator!")
                    continue
                num2 = float(input("Enter the second number: "))
            except ValueError:
                print(Fore.RED + "Please enter valid numbers.")
                continue
            args = argparse.Namespace(num1=num1, operator=op, num2=num2)
            calc(args)

        elif choice == "2":
            name = input("Enter your name: ")
            args = argparse.Namespace(name=name)
            greet(args)

        elif choice == "0":
            print(Fore.RED + "Exiting...")
            break

        else:
            print(Fore.RED + "Invalid choice!")

def main():
    parser = argparse.ArgumentParser(
        prog="mytool",
        description="A clean, minimal cyber multitool CLI."
    )
    parser.add_argument("--menu", action="store_true", help="Open interactive menu.")

    subparsers = parser.add_subparsers(dest="command")

    parser_calc = subparsers.add_parser("calc", help="Perform basic math operations.")
    parser_calc.add_argument("num1", type=float)
    parser_calc.add_argument("operator", choices=["+", "-", "*", "/"])
    parser_calc.add_argument("num2", type=float)
    parser_calc.set_defaults(func=calc)

    parser_greet = subparsers.add_parser("greet", help="Greet the user.")
    parser_greet.add_argument("name", type=str)
    parser_greet.set_defaults(func=greet)

    args = parser.parse_args()

    if args.menu:
        menu()
    elif hasattr(args, "func"):
        args.func(args)
    else:
        parser.print_help()

if __name__ == "__main__":
    main()
