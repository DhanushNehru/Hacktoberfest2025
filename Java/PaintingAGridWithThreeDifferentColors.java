class Solution {
    public int colorTheGrid(int m, int n) {
        int MOD = 1_000_000_007;

        // Step 1: Generate all valid column states
        List<int[]> states = new ArrayList<>();
        generateStates(m, new int[m], 0, states);

        int totalStates = states.size();

        // Step 2: Precompute compatibility between states
        boolean[][] compatible = new boolean[totalStates][totalStates];
        for (int i = 0; i < totalStates; i++) {
            for (int j = 0; j < totalStates; j++) {
                if (isCompatible(states.get(i), states.get(j))) {
                    compatible[i][j] = true;
                }
            }
        }

        // Step 3: DP initialization
        long[] dp = new long[totalStates];
        Arrays.fill(dp, 1);  // first column can be any valid state

        // Step 4: DP transition for each column
        for (int col = 1; col < n; col++) {
            long[] newDp = new long[totalStates];
            for (int curr = 0; curr < totalStates; curr++) {
                for (int prev = 0; prev < totalStates; prev++) {
                    if (compatible[curr][prev]) {
                        newDp[curr] = (newDp[curr] + dp[prev]) % MOD;
                    }
                }
            }
            dp = newDp;
        }

        // Step 5: Sum all valid endings
        long ans = 0;
        for (long val : dp) ans = (ans + val) % MOD;
        return (int) ans;
    }

    // Generate all valid single-column colorings
    private void generateStates(int m, int[] curr, int idx, List<int[]> result) {
        if (idx == m) {
            result.add(curr.clone());
            return;
        }
        for (int color = 0; color < 3; color++) {
            if (idx == 0 || curr[idx - 1] != color) {
                curr[idx] = color;
                generateStates(m, curr, idx + 1, result);
            }
        }
    }

    // Check if two column states are compatible (no same row color)
    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }
}
