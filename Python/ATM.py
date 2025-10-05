
"""
Simple ATM machine simulator.

Data is stored in 'accounts.json' in the same directory. If the file doesn't exist,
sample accounts will be created automatically.
"""
import json
import os
import sys
import getpass
from datetime import datetime
from typing import Dict, Any, List

DATA_FILE = "accounts.json"
MIN_WITHDRAW = 10  # minimum withdrawal amount
CURRENCY = "$"

def now_ts() -> str:
    return datetime.now().isoformat(sep=" ", timespec="seconds")

def load_accounts() -> Dict[str, Any]:
    if not os.path.exists(DATA_FILE):
        # create sample accounts
        accounts = {
            # card_number: {pin, name, balance, transactions: [...]}
            "10010001": {
                "pin": "1234",
                "name": "Alice Example",
                "balance": 1500.00,
                "transactions": [
                    {"ts": now_ts(), "type": "CREATED", "amount": 1500.00, "balance": 1500.00}
                ]
            },
            "10010002": {
                "pin": "4321",
                "name": "Bob Example",
                "balance": 800.00,
                "transactions": [
                    {"ts": now_ts(), "type": "CREATED", "amount": 800.00, "balance": 800.00}
                ]
            }
        }
        save_accounts(accounts)
        return accounts
    with open(DATA_FILE, "r", encoding="utf-8") as f:
        return json.load(f)

def save_accounts(accounts: Dict[str, Any]) -> None:
    # Ensure write atomically (simple)
    tmp = DATA_FILE + ".tmp"
    with open(tmp, "w", encoding="utf-8") as f:
        json.dump(accounts, f, indent=2)
    os.replace(tmp, DATA_FILE)

def authenticate(accounts: Dict[str, Any]) -> str:
    print("=== Welcome to PythonATM ===")
    for attempt in range(3):
        card = input("Enter card number: ").strip()
        if card not in accounts:
            print("Card not recognized. Try again.")
            continue
        pin = getpass.getpass("Enter PIN: ").strip()
        if pin == accounts[card]["pin"]:
            print(f"Login successful. Welcome, {accounts[card]['name']}!\n")
            return card
        else:
            print("Incorrect PIN.")
    print("Too many failed attempts. Exiting.")
    sys.exit(1)

def show_balance(account: Dict[str, Any]) -> None:
    print(f"Current balance: {CURRENCY}{account['balance']:.2f}")

def add_transaction(account: Dict[str, Any], ttype: str, amount: float) -> None:
    account["transactions"].append({
        "ts": now_ts(),
        "type": ttype,
        "amount": round(amount, 2),
        "balance": round(account["balance"], 2)
    })

def withdraw(account: Dict[str, Any]) -> None:
    try:
        amount = float(input("Withdrawal amount: ").strip())
    except ValueError:
        print("Invalid amount.")
        return
    if amount <= 0:
        print("Enter a positive amount.")
        return
    if amount < MIN_WITHDRAW:
        print(f"Minimum withdrawal is {CURRENCY}{MIN_WITHDRAW}")
        return
    if amount > account["balance"]:
        print("Insufficient funds.")
        return
    account["balance"] = round(account["balance"] - amount, 2)
    add_transaction(account, "WITHDRAW", -amount)
    print(f"Please take your cash. New balance: {CURRENCY}{account['balance']:.2f}")

def deposit(account: Dict[str, Any]) -> None:
    try:
        amount = float(input("Deposit amount: ").strip())
    except ValueError:
        print("Invalid amount.")
        return
    if amount <= 0:
        print("Enter a positive amount.")
        return
    account["balance"] = round(account["balance"] + amount, 2)
    add_transaction(account, "DEPOSIT", amount)
    print(f"Deposit accepted. New balance: {CURRENCY}{account['balance']:.2f}")

def transfer(accounts: Dict[str, Any], from_card: str) -> None:
    to_card = input("Transfer to (card number): ").strip()
    if to_card == from_card:
        print("Cannot transfer to the same account.")
        return
    if to_card not in accounts:
        print("Destination account not found.")
        return
    try:
        amount = float(input("Amount to transfer: ").strip())
    except ValueError:
        print("Invalid amount.")
        return
    if amount <= 0:
        print("Enter a positive amount.")
        return
    if amount > accounts[from_card]["balance"]:
        print("Insufficient funds for transfer.")
        return
    # do transfer
    accounts[from_card]["balance"] = round(accounts[from_card]["balance"] - amount, 2)
    add_transaction(accounts[from_card], f"TRANSFER_OUT->{to_card}", -amount)

    accounts[to_card]["balance"] = round(accounts[to_card]["balance"] + amount, 2)
    add_transaction(accounts[to_card], f"TRANSFER_IN<-{from_card}", amount)
    print(f"Transferred {CURRENCY}{amount:.2f} to {accounts[to_card]['name']} ({to_card}).")
    print(f"Your new balance: {CURRENCY}{accounts[from_card]['balance']:.2f}")

def mini_statement(account: Dict[str, Any], lines: int = 10) -> None:
    txs: List[Dict[str, Any]] = account.get("transactions", [])
    print(f"--- Mini Statement (last {lines} transactions) ---")
    for t in txs[-lines:]:
        amt = f"{t['amount']:+.2f}"
        print(f"{t['ts']}\t{t['type']}\t{amt}\tbal:{CURRENCY}{t['balance']:.2f}")
    print("--- End ---")

def change_pin(account: Dict[str, Any]) -> None:
    old = getpass.getpass("Enter current PIN: ").strip()
    if old != account["pin"]:
        print("Incorrect current PIN.")
        return
    new = getpass.getpass("Enter new PIN (4 digits recommended): ").strip()
    if not new.isdigit() or len(new) < 3:
        print("PIN should be numeric and at least 3 digits.")
        return
    confirm = getpass.getpass("Confirm new PIN: ").strip()
    if new != confirm:
        print("PIN confirmation does not match.")
        return
    account["pin"] = new
    add_transaction(account, "PIN_CHANGE", 0.0)
    print("PIN successfully changed.")

def create_account(accounts: Dict[str, Any]) -> None:
    print("=== Create a new account ===")
    name = input("Full name: ").strip()
    while True:
        card = input("Choose card number (8 digits recommended): ").strip()
        if card in accounts:
            print("Card number already exists. Choose another.")
            continue
        if not card:
            print("Card number cannot be empty.")
            continue
        break
    while True:
        pin = getpass.getpass("Choose a PIN: ").strip()
        if not pin.isdigit() or len(pin) < 3:
            print("PIN should be numeric and at least 3 digits.")
            continue
        pin2 = getpass.getpass("Confirm PIN: ").strip()
        if pin != pin2:
            print("PINs do not match.")
            continue
        break
    try:
        initial = float(input("Initial deposit (0 allowed): ").strip() or "0")
    except ValueError:
        print("Invalid amount, setting initial deposit to 0.")
        initial = 0.0
    accounts[card] = {
        "pin": pin,
        "name": name,
        "balance": round(max(0.0, initial), 2),
        "transactions": [
            {"ts": now_ts(), "type": "CREATED", "amount": round(initial, 2), "balance": round(max(0.0, initial), 2)}
        ]
    }
    save_accounts(accounts)
    print(f"Account created successfully. Card number: {card}")

def main_menu(accounts: Dict[str, Any], card: str) -> None:
    while True:
        account = accounts[card]
        print("\nSelect an option:")
        print("1) Check balance")
        print("2) Withdraw")
        print("3) Deposit")
        print("4) Transfer")
        print("5) Mini statement")
        print("6) Change PIN")
        print("7) Exit")
        choice = input("Choice: ").strip()
        if choice == "1":
            show_balance(account)
        elif choice == "2":
            withdraw(account)
            save_accounts(accounts)
        elif choice == "3":
            deposit(account)
            save_accounts(accounts)
        elif choice == "4":
            transfer(accounts, card)
            save_accounts(accounts)
        elif choice == "5":
            mini_statement(account)
        elif choice == "6":
            change_pin(account)
            save_accounts(accounts)
        elif choice == "7":
            print("Thank you for using PythonATM. Goodbye!")
            save_accounts(accounts)
            break
        else:
            print("Invalid option. Try again.")

def main():
    accounts = load_accounts()
    while True:
        print("\n=== PythonATM Main ===")
        print("1) Login")
        print("2) Create new account")
        print("3) Quit")
        choice = input("Select: ").strip()
        if choice == "1":
            card = authenticate(accounts)
            main_menu(accounts, card)
        elif choice == "2":
            create_account(accounts)
        elif choice == "3":
            print("Goodbye.")
            break
        else:
            print("Invalid option.")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nInterrupted. Exiting.")
