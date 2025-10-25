def maxProfit(self, prices):
        min_price = float('inf')
        max_profit = 0
        
        for price in prices:
            """if price lower than yesterday update price"""
            if price < min_price:
                min_price = price
            elif price - min_price > max_profit:
                max_profit = price - min_price
        
        return max_profit