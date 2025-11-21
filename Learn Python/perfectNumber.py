num = int(input("Enter a number: "))
sum = 0
upperLimit = int(num/2)+1
for i in range(1, upperLimit):
    if num%i == 0:
        sum += i
if sum == num:
    print("It is a perfect number")
else:
    print("It is not a perfect number")