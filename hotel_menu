#define menu of a restaurant
menu = {
    'Pizza':50,
    'Burger':30,
    'Pasta':40,
    'Salad':20,
    'Soda':10
}

print("Welcome to the RIMU Restaurant!")
print("Here is our menu:")
print("Pizza: rs.50\n Burger: rs.30\n Pasta: rs.40\n Salad: rs.20\n Soda: rs.10")

order_total = 0

item_1= input("Enter the  item you want to order: ")
if item_1 in menu:
    order_total += menu[item_1]
    print(f"{item_1} added to your order. Price: rs.{menu[item_1]}")
else:
    print(f"Sorry, we don't have {item_1} on the menu.")

another_order= input("do you  you want another order?, yes or no: ")

if another_order.lower() == 'yes':
    item_2= input("Enter the  item you want to order: ")
    if item_2 in menu:
        order_total += menu[item_2]
        print(f"{item_2} added to your order. Price: rs.{menu[item_2]}")
    else:
        print(f"Sorry, we don't have {item_2} on the menu.")
    
print(f"Your total order amount is: rs.{order_total}")

print("Thank you for your order!")
