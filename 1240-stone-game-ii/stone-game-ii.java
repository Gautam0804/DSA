class Solution {
    public int stoneGameII(int[] piles) {

         int n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M]
        int[][] dp = new int[n + 1][n + 1];

        // Fill from right to left
        for (int i = n - 1; i >= 0; i--) {

            for (int M = n; M >= 1; M--) {

                // Can take all remaining piles
                if (i + 2 * M >= n) {
                    dp[i][M] = suffix[i];
                    continue;
                }

                int best = 0;

                for (int X = 1; X <= 2 * M && i + X <= n; X++) {

                    int newM = Math.max(M, X);

                    int opponent = dp[i + X][newM];

                    int current = suffix[i] - opponent;

                    best = Math.max(best, current);
                }

                dp[i][M] = best;
            }
        }

        return dp[0][1];
        
    }
}