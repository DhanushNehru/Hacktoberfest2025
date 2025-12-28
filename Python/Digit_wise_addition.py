def addNumbers(num1, num2):
    result = 0
    carry = 0
    place = 1
    
    while num1 > 0 or num2 > 0 or carry > 0:
        d1 = num1 % 10
        d2 = num2 % 10
        
        total = d1 + d2 + carry
        digit = total % 10
        carry = total // 10
        
        result = result + digit * place
        place *= 10
        
        num1 //= 10
        num2 //= 10
    
    return result
