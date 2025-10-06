class Solution(object):
    def firstUniqChar(self, s):
        new=[]
        for i in s:
            if i not in new :
                new.append(i)
        for i in new:
            k=0
            for j in s:
                if i==j:
                    k=k+1
            if k==1:
                return s.index(i)
        return -1

        """
        :type s: str
        :rtype: int
        """
        
