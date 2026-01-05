class Solution {
    public int maxScore(String s) {
        int n = s.length();
        int maxScore = 0;

        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                totalOnes++;
            }
        }

        int leftZeros = 0; 
        int rightOnes = totalOnes; 

        
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++;
            } else {
                rightOnes--;
            }

            
            int currentScore = leftZeros + rightOnes;
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxScore("011101")); 
        System.out.println(solution.maxScore("00111"));  
        System.out.println(solution.maxScore("1111"));   
    }
}
