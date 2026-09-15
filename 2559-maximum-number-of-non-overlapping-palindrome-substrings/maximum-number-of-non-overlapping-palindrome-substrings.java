class Solution {

    public int maxPalindromes(String s, int k) {

        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        int count = 0;
        int lastEnd = -1;

        for (int end = 0; end < n; end++) {

            for (int start = end; start >= 0; start--) {

                if (s.charAt(start) == s.charAt(end) &&
                    (end - start <= 1 || dp[start + 1][end - 1])) {

                    dp[start][end] = true;

                    int length = end - start + 1;

                    if (length >= k && start > lastEnd) {

                        count++;

                        lastEnd = end;
                    }
                }
            }
        }

        return count;
    }
}