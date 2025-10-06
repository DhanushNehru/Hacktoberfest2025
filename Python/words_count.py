class Solution(object):
    def mostWordsFound(self, sentences):
        num=0
        for i in sentences:
            count=0
            for j in i:
                if j==' ':
                    count=count+1
            if count>num:
                num=count
        return num+1



        """
        :type sentences: List[str]
        :rtype: int
        """
        