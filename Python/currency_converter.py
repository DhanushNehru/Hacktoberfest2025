from forex_python.converter import CurrencyRates
c = CurrencyRates()

amount = int(input("Enter the amount: "))
from_currency = input("From : ").upper()
to_currency = input("To : ").upper()
result = c.convert(from_currency, to_currency, amount)

print("\n")
print(from_currency, "to", to_currency, ": ", result)
