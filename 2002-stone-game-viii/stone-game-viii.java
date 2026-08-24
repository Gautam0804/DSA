class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Prefix sum
        int[] prefix = new int[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        /*
         * dp represents the best score difference
         * the current player can achieve.
         *
         * Initially, if Alice takes all stones:
         * score = prefix[n - 1]
         */
        int dp = prefix[n - 1];

        /*
         * We process from right to left.
         *
         * At position i, the player can:
         *
         * 1. Take prefix[ i ]
         *    => prefix[i] - dp
         *
         * 2. Keep the previous best dp
         *
         * Therefore:
         *
         * dp = max(dp, prefix[i] - dp)
         */
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }

        return dp;
    }
}