"""
Personal Budget Tracker - A Real-World Financial Management Tool
Author: Hariom Kumar Pandit (hari7261)
Problem: Many people struggle to track their expenses and stick to budgets
Solution: Interactive budget tracker with expense categorization and savings goals

Features:
- Track income and expenses by category
- Set budget limits for different categories
- Monitor savings goals
- Generate spending reports
- Alert when budget limits are exceeded
"""

import json
import datetime
from typing import Dict, List, Tuple

class PersonalBudgetTracker:
    def __init__(self, data_file: str = "budget_data.json"):
        self.data_file = data_file
        self.data = self.load_data()
        
    def load_data(self) -> Dict:
        """Load existing budget data or create new structure"""
        try:
            with open(self.data_file, 'r') as file:
                return json.load(file)
        except FileNotFoundError:
            return {
                "income": 0,
                "expenses": {},
                "budgets": {},
                "savings_goal": 0,
                "current_savings": 0,
                "transactions": []
            }
    
    def save_data(self):
        """Save budget data to file"""
        with open(self.data_file, 'w') as file:
            json.dump(self.data, file, indent=2)
    
    def set_monthly_income(self, amount: float):
        """Set monthly income"""
        self.data["income"] = amount
        print(f"✅ Monthly income set to ${amount:,.2f}")
        self.save_data()
    
    def set_category_budget(self, category: str, budget: float):
        """Set budget limit for a category"""
        self.data["budgets"][category.lower()] = budget
        print(f"✅ Budget for '{category}' set to ${budget:,.2f}")
        self.save_data()
    
    def add_expense(self, category: str, amount: float, description: str = ""):
        """Add an expense to a category"""
        category = category.lower()
        
        # Initialize category if it doesn't exist
        if category not in self.data["expenses"]:
            self.data["expenses"][category] = 0
        
        self.data["expenses"][category] += amount
        
        # Record transaction
        transaction = {
            "date": datetime.datetime.now().isoformat(),
            "type": "expense",
            "category": category,
            "amount": amount,
            "description": description
        }
        self.data["transactions"].append(transaction)
        
        print(f"✅ Added ${amount:.2f} expense to '{category}'")
        
        # Check budget warning
        self.check_budget_warning(category)
        self.save_data()
    
    def check_budget_warning(self, category: str):
        """Check if category spending exceeds budget"""
        if category in self.data["budgets"]:
            budget = self.data["budgets"][category]
            spent = self.data["expenses"].get(category, 0)
            
            if spent > budget:
                excess = spent - budget
                print(f"⚠️  WARNING: '{category}' budget exceeded by ${excess:.2f}!")
            elif spent > budget * 0.8:  # 80% warning
                remaining = budget - spent
                print(f"💡 ALERT: Only ${remaining:.2f} left in '{category}' budget")
    
    def set_savings_goal(self, goal: float):
        """Set monthly savings goal"""
        self.data["savings_goal"] = goal
        print(f"✅ Savings goal set to ${goal:,.2f}")
        self.save_data()
    
    def add_savings(self, amount: float):
        """Add to current savings"""
        self.data["current_savings"] += amount
        
        transaction = {
            "date": datetime.datetime.now().isoformat(),
            "type": "savings",
            "category": "savings",
            "amount": amount,
            "description": "Savings deposit"
        }
        self.data["transactions"].append(transaction)
        
        print(f"✅ Added ${amount:.2f} to savings")
        self.check_savings_progress()
        self.save_data()
    
    def check_savings_progress(self):
        """Check progress towards savings goal"""
        goal = self.data["savings_goal"]
        current = self.data["current_savings"]
        
        if goal > 0:
            progress = (current / goal) * 100
            print(f"💰 Savings Progress: ${current:.2f} / ${goal:.2f} ({progress:.1f}%)")
            
            if current >= goal:
                print("🎉 Congratulations! You've reached your savings goal!")
    
    def generate_monthly_report(self) -> str:
        """Generate comprehensive monthly budget report"""
        report = "\n" + "="*50
        report += "\n📊 MONTHLY BUDGET REPORT\n"
        report += "="*50 + "\n"
        
        # Income section
        income = self.data["income"]
        report += f"💰 Monthly Income: ${income:,.2f}\n\n"
        
        # Expenses section
        total_expenses = sum(self.data["expenses"].values())
        report += "📝 EXPENSES BY CATEGORY:\n"
        report += "-" * 30 + "\n"
        
        for category, amount in self.data["expenses"].items():
            budget = self.data["budgets"].get(category, 0)
            status = "✅" if amount <= budget else "❌"
            if budget > 0:
                percentage = (amount / budget) * 100
                report += f"{status} {category.title()}: ${amount:.2f} / ${budget:.2f} ({percentage:.1f}%)\n"
            else:
                report += f"⚪ {category.title()}: ${amount:.2f} (No budget set)\n"
        
        report += f"\nTotal Expenses: ${total_expenses:.2f}\n\n"
        
        # Financial summary
        remaining = income - total_expenses
        report += "💡 FINANCIAL SUMMARY:\n"
        report += "-" * 20 + "\n"
        report += f"Remaining Income: ${remaining:.2f}\n"
        
        if remaining > 0:
            report += "✅ You're within budget! 🎉\n"
        else:
            report += f"❌ Over budget by ${abs(remaining):.2f} ⚠️\n"
        
        # Savings section
        savings_goal = self.data["savings_goal"]
        current_savings = self.data["current_savings"]
        
        if savings_goal > 0:
            progress = (current_savings / savings_goal) * 100
            report += f"\n🎯 Savings Goal: ${current_savings:.2f} / ${savings_goal:.2f} ({progress:.1f}%)\n"
        
        return report
    
    def get_spending_trends(self) -> List[Tuple[str, float]]:
        """Get spending trends by category"""
        return sorted(self.data["expenses"].items(), key=lambda x: x[1], reverse=True)
    
    def reset_monthly_data(self):
        """Reset data for new month (keeps budgets and goals)"""
        self.data["expenses"] = {}
        self.data["current_savings"] = 0
        self.data["transactions"] = []
        print("✅ Monthly data reset for new month")
        self.save_data()

def main():
    """Interactive budget tracker demo"""
    print("🏦 Personal Budget Tracker - Real-World Financial Management")
    print("=" * 60)
    
    tracker = PersonalBudgetTracker()
    
    while True:
        print("\n📋 MENU OPTIONS:")
        print("1. Set Monthly Income")
        print("2. Set Category Budget")
        print("3. Add Expense")
        print("4. Set Savings Goal")
        print("5. Add to Savings")
        print("6. View Monthly Report")
        print("7. View Spending Trends")
        print("8. Reset Monthly Data")
        print("9. Exit")
        
        try:
            choice = input("\nSelect option (1-9): ").strip()
            
            if choice == "1":
                income = float(input("Enter monthly income: $"))
                tracker.set_monthly_income(income)
                
            elif choice == "2":
                category = input("Enter category name: ").strip()
                budget = float(input(f"Enter budget for {category}: $"))
                tracker.set_category_budget(category, budget)
                
            elif choice == "3":
                category = input("Enter expense category: ").strip()
                amount = float(input("Enter expense amount: $"))
                description = input("Enter description (optional): ").strip()
                tracker.add_expense(category, amount, description)
                
            elif choice == "4":
                goal = float(input("Enter monthly savings goal: $"))
                tracker.set_savings_goal(goal)
                
            elif choice == "5":
                amount = float(input("Enter savings amount: $"))
                tracker.add_savings(amount)
                
            elif choice == "6":
                print(tracker.generate_monthly_report())
                
            elif choice == "7":
                trends = tracker.get_spending_trends()
                print("\n📈 SPENDING TRENDS (Highest to Lowest):")
                print("-" * 40)
                for category, amount in trends:
                    print(f"{category.title()}: ${amount:.2f}")
                
            elif choice == "8":
                confirm = input("Reset monthly data? (y/n): ").lower()
                if confirm == 'y':
                    tracker.reset_monthly_data()
                
            elif choice == "9":
                print("👋 Thank you for using Personal Budget Tracker!")
                break
                
            else:
                print("❌ Invalid option. Please try again.")
                
        except ValueError:
            print("❌ Invalid input. Please enter valid numbers.")
        except KeyboardInterrupt:
            print("\n👋 Goodbye!")
            break

# Example usage and demonstration
if __name__ == "__main__":
    print("🚀 DEMO: Personal Budget Tracker")
    print("Real-world problem: Track expenses and manage budgets")
    print("-" * 50)
    
    # Create demo tracker
    demo_tracker = PersonalBudgetTracker("demo_budget.json")
    
    # Demo setup
    demo_tracker.set_monthly_income(5000)
    demo_tracker.set_category_budget("groceries", 600)
    demo_tracker.set_category_budget("entertainment", 200)
    demo_tracker.set_category_budget("transportation", 300)
    demo_tracker.set_savings_goal(1000)
    
    # Demo expenses
    demo_tracker.add_expense("groceries", 150, "Weekly shopping")
    demo_tracker.add_expense("entertainment", 50, "Movie tickets")
    demo_tracker.add_expense("transportation", 80, "Gas")
    demo_tracker.add_expense("groceries", 120, "Organic foods")
    demo_tracker.add_savings(500)
    
    # Show report
    print(demo_tracker.generate_monthly_report())
    
    print("\n" + "="*50)
    print("🎯 REAL-WORLD APPLICATIONS:")
    print("• Personal expense tracking")
    print("• Budget planning and monitoring") 
    print("• Savings goal management")
    print("• Financial awareness building")
    print("• Overspending prevention")
    print("="*50)
    
    # Uncomment to run interactive mode
    # main()