import java.util.*;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // dp[i][j] = possible balances at cell (i, j)
        Set<Integer>[][] dp = new HashSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }

        // Starting cell
        int startBalance = grid[0][0] == '(' ? 1 : -1;

        // Can't start with ')'
        if (startBalance < 0) {
            return false;
        }

        dp[0][0].add(startBalance);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change = grid[i][j] == '(' ? 1 : -1;

                // From top
                if (i > 0) {
                    for (int balance : dp[i - 1][j]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0) {
                            dp[i][j].add(newBalance);
                        }
                    }
                }

                // From left
                if (j > 0) {
                    for (int balance : dp[i][j - 1]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0) {
                            dp[i][j].add(newBalance);
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1].contains(0);
    }
}