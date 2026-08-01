class Solution {

    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;
        Integer[][] dp = new Integer[n][n];

        return solve(nums, 0, n - 1, dp) >= 0;
    }

    private int solve(int[] nums, int left, int right, Integer[][] dp) {

        // Base case
        if (left == right) {
            return nums[left];
        }

        // Already calculated
        if (dp[left][right] != null) {
            return dp[left][right];
        }

        // Pick left
        int pickLeft = nums[left] - solve(nums, left + 1, right, dp);

        // Pick right
        int pickRight = nums[right] - solve(nums, left, right - 1, dp);

        // Store the best result
        dp[left][right] = Math.max(pickLeft, pickRight);

        return dp[left][right];
    }
}