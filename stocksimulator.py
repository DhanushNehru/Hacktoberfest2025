import random

class StockSimulator:
    def __init__(self):
        self.balance = 1000  # Starting balance
        self.stocks_owned = 0  # Number of stocks owned
        self.stock_price = 100  # Starting stock price

    def display_status(self, day):
        print(f"\nDay {day}")
        print(f"Stock price: ${self.stock_price:.2f}")
        print(f"Your balance: ${self.balance:.2f}")
        print(f"Stocks owned: {self.stocks_owned}")

    def buy_stocks(self, amount):
        total_cost = amount * self.stock_price
        if total_cost > self.balance:
            print("Not enough balance to buy that many stocks.")
        else:
            self.balance -= total_cost
            self.stocks_owned += amount
            print(f"Bought {amount} stocks at ${self.stock_price:.2f} each.")

    def sell_stocks(self, amount):
        if amount > self.stocks_owned:
            print("You don't own that many stocks.")
        else:
            total_gain = amount * self.stock_price
            self.balance += total_gain
            self.stocks_owned -= amount
            print(f"Sold {amount} stocks at ${self.stock_price:.2f} each.")

    def simulate_day(self):
        # Stock price changes randomly between -5% and +5%
        change = random.uniform(-0.05, 0.05)
        self.stock_price += self.stock_price * change
        if self.stock_price < 1:
            self.stock_price = 1  # Ensure stock price doesn't go below $1

    def play(self, days):
        for day in range(1, days + 1):
            self.simulate_day()
            self.display_status(day)

            # Get user choice
            choice = input("\nDo you want to (b)uy, (s)ell, or (p)ass? ").lower()

            if choice == 'b':
                amount = int(input("How many stocks would you like to buy? "))
                self.buy_stocks(amount)
            elif choice == 's':
                amount = int(input("How many stocks would you like to sell? "))
                self.sell_stocks(amount)
            elif choice == 'p':
                print("Passing the day.")

        # Final status after simulation
        print("\nSimulation over!")
        print(f"Final balance: ${self.balance:.2f}")
        print(f"Final stocks owned: {self.stocks_owned}")
        print(f"Final stock price: ${self.stock_price:.2f}")
        print(f"Total portfolio value: ${self.balance + (self.stocks_owned * self.stock_price):.2f}")

# Start the Stock Simulator with a set number of days
simulator = StockSimulator()
simulator.play(10)  # Simulate 10 days of stock trading
