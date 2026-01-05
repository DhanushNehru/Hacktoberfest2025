class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefixSum = new int[n];
        
        prefixSum[0] = isVowel(words[0]);
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + isVowel(words[i]);
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int res = prefixSum[r];
            if (l > 0) {
                res -= prefixSum[l - 1];
            }
            ans[i] = res;
        }
        
        return ans;
    }
    public int isVowel(String word) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        if (set.contains(first) && set.contains(last)) {
            return 1;
        }
        return 0;
    }
}
