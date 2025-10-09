import json
import datetime
from typing import Dict, List

class PersonalFinanceTracker:
    def __init__(self, filename: str = "finance_data.json"):
        self.filename = filename
        self.data = self.load_data()
        
    def load_data(self) -> Dict:
        """Load financial data from JSON file"""
        try:
            with open(self.filename, 'r') as f:
                return json.load(f)
        except FileNotFoundError:
            # Initialize with default structure
            return {
                'transactions': [],
                'categories': {
                    'income': ['Salary', 'Freelance', 'Investment', 'Gift', 'Bonus', 'Side Hustle'],
                    'expense': ['Food', 'Transport', 'Entertainment', 'Bills', 'Shopping', 'Healthcare', 'Education']
                },
                'budgets': {},
                'savings_goals': []
            }
    
    def save_data(self):
        """Save data to JSON file"""
        with open(self.filename, 'w') as f:
            json.dump(self.data, f, indent=2)
    
    def add_transaction(self, amount: float, category: str, transaction_type: str, description: str = ""):
        """Add a new transaction (income or expense)"""
        if transaction_type not in ['income', 'expense']:
            print("❌ Error: Transaction type must be 'income' or 'expense'")
            return
            
        transaction = {
            'id': len(self.data['transactions']) + 1,
            'date': datetime.datetime.now().isoformat(),
            'amount': amount,
            'category': category,
            'type': transaction_type,
            'description': description
        }
        
        self.data['transactions'].append(transaction)
        self.save_data()
        print(f"✅ Added {transaction_type}: ${amount:.2f} - {category}")
    
    def get_balance(self) -> float:
        """Calculate current balance"""
        income = sum(t['amount'] for t in self.data['transactions'] if t['type'] == 'income')
        expenses = sum(t['amount'] for t in self.data['transactions'] if t['type'] == 'expense')
        return income - expenses
    
    def get_monthly_summary(self, year: int = None, month: int = None) -> Dict:
        """Get monthly income, expenses, and balance"""
        if year is None:
            year = datetime.datetime.now().year
        if month is None:
            month = datetime.datetime.now().month
            
        monthly_transactions = [
            t for t in self.data['transactions']
            if datetime.datetime.fromisoformat(t['date']).year == year
            and datetime.datetime.fromisoformat(t['date']).month == month
        ]
        
        income = sum(t['amount'] for t in monthly_transactions if t['type'] == 'income')
        expenses = sum(t['amount'] for t in monthly_transactions if t['type'] == 'expense')
        
        return {
            'income': income,
            'expenses': expenses,
            'balance': income - expenses,
            'transaction_count': len(monthly_transactions)
        }
    
    def get_category_spending(self) -> Dict[str, float]:
        """Get spending by category"""
        spending = {}
        for transaction in self.data['transactions']:
            if transaction['type'] == 'expense':
                category = transaction['category']
                spending[category] = spending.get(category, 0) + transaction['amount']
        return spending
    
    def set_budget(self, category: str, amount: float):
        """Set monthly budget for a category"""
        self.data['budgets'][category] = amount
        self.save_data()
        print(f"💰 Budget set for {category}: ${amount:.2f}")
    
    def check_budgets(self):
        """Check if any budgets are exceeded"""
        current_month = datetime.datetime.now().month
        current_year = datetime.datetime.now().year
        
        monthly_expenses = {}
        for transaction in self.data['transactions']:
            if (transaction['type'] == 'expense' and 
                datetime.datetime.fromisoformat(transaction['date']).month == current_month and
                datetime.datetime.fromisoformat(transaction['date']).year == current_year):
                category = transaction['category']
                monthly_expenses[category] = monthly_expenses.get(category, 0) + transaction['amount']
        
        print("\n--- Budget Check ---")
        any_exceeded = False
        for category, budget in self.data['budgets'].items():
            spent = monthly_expenses.get(category, 0)
            percentage = (spent / budget) * 100 if budget > 0 else 0
            
            if spent > budget:
                print(f"❌ {category}: ${spent:.2f} / ${budget:.2f} ({percentage:.1f}% - OVER BUDGET!)")
                any_exceeded = True
            else:
                print(f"✅ {category}: ${spent:.2f} / ${budget:.2f} ({percentage:.1f}%)")
        
        if not any_exceeded:
            print("🎉 All budgets are within limits!")
    
    def add_savings_goal(self, name: str, target_amount: float, deadline: str):
        """Add a savings goal"""
        goal = {
            'name': name,
            'target_amount': target_amount,
            'current_amount': 0,
            'deadline': deadline,
            'created_date': datetime.datetime.now().isoformat()
        }
        self.data['savings_goals'].append(goal)
        self.save_data()
        print(f"🎯 Savings goal added: {name} - ${target_amount:.2f} by {deadline}")
    
    def contribute_to_goal(self, goal_name: str, amount: float):
        """Contribute money to a savings goal"""
        for goal in self.data['savings_goals']:
            if goal['name'].lower() == goal_name.lower():
                goal['current_amount'] += amount
                self.save_data()
                progress = (goal['current_amount'] / goal['target_amount']) * 100
                print(f"💰 Contributed ${amount:.2f} to {goal['name']}")
                print(f"📊 Progress: {progress:.1f}% (${goal['current_amount']:.2f}/${goal['target_amount']:.2f})")
                return
        print(f"❌ Goal '{goal_name}' not found")
    
    def view_savings_goals(self):
        """View all savings goals and their progress"""
        if not self.data['savings_goals']:
            print("No savings goals set yet.")
            return
            
        print("\n--- Savings Goals ---")
        for goal in self.data['savings_goals']:
            progress = (goal['current_amount'] / goal['target_amount']) * 100
            remaining = goal['target_amount'] - goal['current_amount']
            
            print(f"🎯 {goal['name']}:")
            print(f"   Progress: {progress:.1f}% (${goal['current_amount']:.2f}/${goal['target_amount']:.2f})")
            print(f"   Remaining: ${remaining:.2f}")
            print(f"   Deadline: {goal['deadline']}")
            print()
    
    def generate_report(self):
        """Generate a comprehensive financial report"""
        balance = self.get_balance()
        spending = self.get_category_spending()
        monthly = self.get_monthly_summary()
        
        print("\n" + "="*60)
        print("                FINANCIAL REPORT")
        print("="*60)
        print(f"💰 Current Balance: ${balance:.2f}")
        print(f"📊 This Month - Income: ${monthly['income']:.2f} | "
              f"Expenses: ${monthly['expenses']:.2f} | "
              f"Net: ${monthly['balance']:.2f}")
        
        print("\n--- Spending by Category ---")
        if spending:
            for category, amount in sorted(spending.items(), key=lambda x: x[1], reverse=True):
                print(f"  {category}: ${amount:.2f}")
        else:
            print("  No expenses recorded yet.")
        
        print("\n--- Savings Goals Progress ---")
        if self.data['savings_goals']:
            for goal in self.data['savings_goals']:
                progress = (goal['current_amount'] / goal['target_amount']) * 100
                print(f"  {goal['name']}: ${goal['current_amount']:.2f} / ${goal['target_amount']:.2f} ({progress:.1f}%)")
        else:
            print("  No savings goals set yet.")
        
        self.check_budgets()
    
    def view_categories(self):
        """View available categories"""
        print("\n--- Income Categories ---")
        for category in self.data['categories']['income']:
            print(f"  💵 {category}")
        
        print("\n--- Expense Categories ---")
        for category in self.data['categories']['expense']:
            print(f"  💸 {category}")

def main():
    tracker = PersonalFinanceTracker()
    
    print("="*50)
    print("    PERSONAL FINANCE TRACKER")
    print("="*50)
    print("Welcome! Your financial data is stored in 'finance_data.json'")
    
    while True:
        print("\n" + "="*40)
        print("          MAIN MENU")
        print("="*40)
        print("1. 💵 Add Income")
        print("2. 💸 Add Expense")
        print("3. 📊 View Balance & Summary")
        print("4. 🎯 Savings Goals")
        print("5. 📋 Set/Check Budgets")
        print("6. 📈 Generate Full Report")
        print("7. 🗂️ View Categories")
        print("8. 🚪 Exit")
        
        choice = input("\nChoose an option (1-8): ").strip()
        
        if choice == '1':
            print("\n--- Add Income ---")
            try:
                amount = float(input("Enter income amount: $"))
                tracker.view_categories()
                category = input("Enter category: ").strip()
                description = input("Enter description: ").strip()
                tracker.add_transaction(amount, category, 'income', description)
            except ValueError:
                print("❌ Please enter a valid number for amount.")
                
        elif choice == '2':
            print("\n--- Add Expense ---")
            try:
                amount = float(input("Enter expense amount: $"))
                tracker.view_categories()
                category = input("Enter category: ").strip()
                description = input("Enter description: ").strip()
                tracker.add_transaction(amount, category, 'expense', description)
            except ValueError:
                print("❌ Please enter a valid number for amount.")
                
        elif choice == '3':
            print("\n--- Financial Summary ---")
            balance = tracker.get_balance()
            monthly = tracker.get_monthly_summary()
            
            print(f"💰 Current Balance: ${balance:.2f}")
            print(f"📅 This Month:")
            print(f"   Income: ${monthly['income']:.2f}")
            print(f"   Expenses: ${monthly['expenses']:.2f}")
            print(f"   Net: ${monthly['balance']:.2f}")
            print(f"   Transactions: {monthly['transaction_count']}")
            
        elif choice == '4':
            print("\n--- Savings Goals ---")
            print("1. Add New Goal")
            print("2. Contribute to Goal")
            print("3. View All Goals")
            
            sub_choice = input("Choose (1-3): ").strip()
            
            if sub_choice == '1':
                name = input("Enter goal name: ").strip()
                try:
                    target = float(input("Enter target amount: $"))
                    deadline = input("Enter deadline (YYYY-MM-DD): ").strip()
                    tracker.add_savings_goal(name, target, deadline)
                except ValueError:
                    print("❌ Please enter a valid number for target amount.")
                    
            elif sub_choice == '2':
                goal_name = input("Enter goal name: ").strip()
                try:
                    amount = float(input("Enter contribution amount: $"))
                    tracker.contribute_to_goal(goal_name, amount)
                except ValueError:
                    print("❌ Please enter a valid number for amount.")
                    
            elif sub_choice == '3':
                tracker.view_savings_goals()
            else:
                print("❌ Invalid choice.")
                
        elif choice == '5':
            print("\n--- Budget Management ---")
            print("1. Set New Budget")
            print("2. Check Current Budgets")
            
            sub_choice = input("Choose (1-2): ").strip()
            
            if sub_choice == '1':
                category = input("Enter category: ").strip()
                try:
                    amount = float(input("Enter monthly budget: $"))
                    tracker.set_budget(category, amount)
                except ValueError:
                    print("❌ Please enter a valid number for budget.")
                    
            elif sub_choice == '2':
                tracker.check_budgets()
            else:
                print("❌ Invalid choice.")
                
        elif choice == '6':
            tracker.generate_report()
            
        elif choice == '7':
            tracker.view_categories()
            
        elif choice == '8':
            print("\n💼 Thank you for using Personal Finance Tracker!")
            print("💾 Your data has been saved to 'finance_data.json'")
            print("👋 Goodbye! Keep tracking your financial goals! 💰")
            break
            
        else:
            print("❌ Invalid choice. Please enter a number between 1-8.")

if __name__ == "__main__":
    main()