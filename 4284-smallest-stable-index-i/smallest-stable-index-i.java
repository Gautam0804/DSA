class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // Suffix minimum
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Prefix maximum + check stability
        int prefixMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);

            int score = prefixMax - suffixMin[i];

            if (score <= k) {
                return i;
            }
        }

        return -1;
    }
}