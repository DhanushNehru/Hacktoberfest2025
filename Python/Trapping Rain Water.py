class Solution:
    def trap_DP(self, height: List[int]) -> int:
        if not height:
            return 0
        
        n = len(height)
        total_water = 0
        
        # 1. Pre-compute max heights from the left
        max_left = [0] * n
        max_left[0] = height[0]
        for i in range(1, n):
            max_left[i] = max(max_left[i-1], height[i])
            
        # 2. Pre-compute max heights from the right
        max_right = [0] * n
        max_right[n-1] = height[n-1]
        for i in range(n-2, -1, -1):
            max_right[i] = max(max_right[i+1], height[i])
            
        # 3. Calculate trapped water for each bar
        for i in range(n):
            water_level = min(max_left[i], max_right[i])
            trapped_water = water_level - height[i]
            if trapped_water > 0:
                total_water += trapped_water
                
        return total_water
