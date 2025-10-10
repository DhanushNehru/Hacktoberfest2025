 
#Conditional statements in Python  

'''[1]Python supports the usual logical conditions from mathematics:

Equals: a == b
Not Equals: a != b
Less than: a < b
Less than or equal to: a <= b
Greater than: a > b
Greater than or equal to: a >= b

These conditions can be used in several ways, most commonly in "if statements" and loops.
'''

#If and else statements:

a = 18
if a == 18:
    print("You are an adult")
else:
    print("You are a minor")


#If elif and else statements:
a = 50
b = 40

if a > b:
    print(f"{a} is greater than {b}")
elif a == b:
    print(f"{b} are equal {a}")
else:
    print(f"{b} is greater than {a}")
    
#Pass statement:
age = 20
if age >= 18:
    pass  # Placeholder for future code
else:
    print("You are a minor")

#And operator:
a = 100
b = 40
c = 100

if a > b and c == a:
    print(f"A: {a} is greater than B : {b} and equal to C :{c} ") 

#Or operator:
a = 100
b = 40
c = 200
if a > b or c == a:
    print(f"At least one condition is satisfied")
else:
    print("Condition not satisfied")


#Not operator:
is_raining = False
if not is_raining:
    print("Let's go for a walk!")

#Nested if statements:
age = 20
has_license = True

if age >= 18:
    if has_license:
        print("You can drive")
    else:
        print("You need a license")

#Thruthy / Falsy values:
name = ""
if name:
    print(f"Hello {name}")
else:
    print("No name provided")

#Ternary Operator :
age = 20
status = "adult" if age >= 18 else "minor"
print(f"You are an {status}")



    
#References:
#[1]: Into note copied from https://www.w3schools.com/python/python_conditions.asp 