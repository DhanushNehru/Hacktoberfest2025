def reverse(num):
    store=0
    while num!=0:
        store*=10
        store+=num%10
        num=num//10
    return store

num=int(input())
reverseNum = reverse(num)
if num == reverseNum:
    print("Yes")
else:
    print("No")