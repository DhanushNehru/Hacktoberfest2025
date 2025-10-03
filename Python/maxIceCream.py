class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        count = [0]*(max(costs)+1)
        result = 0
        for i in costs:
            count[i] += 1
        for i in range(len(count)):
            while count[i] > 0 and coins-i >= 0:
                coins -= i
                count[i] -= 1
                result += 1
        return result
