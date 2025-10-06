#take input only for a non-negative number
n=int(input("Enter a number: "))

if n<0:
    while n<0:
        print("Please enter a positive integer!")
        n=int(input("Enter a number: "))

#reverse the number
result=0
a=n
while n!=0:
    digit=n%10
    result=result*10 + digit
    n=n//10
    k=result

if k==a:
    print(a, " is a palindrome")
else: 
    print(a," is not a plaindrome")