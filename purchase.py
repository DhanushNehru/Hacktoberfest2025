#In the below code we are invoking the getter method using a reference variable. But the self is not used inside the method at all.

class Mobile:
    __discount = 50
    def __init__(self, price, brand):
        self.price = price
        self.brand = brand

    def purchase(self):
        total = self.price - self.price * Mobile.__discount / 100
        print ("Total is ",total)

    def get_discount(self):
        return Mobile.__discount

    def set_discount(self,discount):
        Mobile.__discount = discount

mob1=Mobile(20000, "Apple")
mob2=Mobile(30000, "Apple")
mob3=Mobile(5000, "Samsung")

print(mob1.get_discount())
