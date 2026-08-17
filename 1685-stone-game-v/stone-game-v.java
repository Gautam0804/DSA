class Solution {

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Prefix sum for O(1) range-sum calculation
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[i][j] = maximum score Alice can obtain
        // from subarray i...j
        int[][] dp = new int[n][n];

        // Length of the current interval
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                // Try every possible split
                for (int k = i; k < j; k++) {

                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {

                        dp[i][j] = Math.max(
                            dp[i][j],
                            leftSum + dp[i][k]
                        );

                    } else if (leftSum > rightSum) {

                        dp[i][j] = Math.max(
                            dp[i][j],
                            rightSum + dp[k + 1][j]
                        );

                    } else {

                        dp[i][j] = Math.max(
                            dp[i][j],
                            Math.max(
                                leftSum + dp[i][k],
                                rightSum + dp[k + 1][j]
                            )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}