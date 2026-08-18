class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;

        // diff[x][i] = difference array for number x
        int[][] diff = new int[51][n + 1];

        // For every occurrence nums[i]
        for (int i = 0; i < n; i++) {

            int x = nums[i];

            // Window start positions that contain index i
            int start = Math.max(0, i - k + 1);
            int end = Math.min(i, n - k);

            if (start <= end) {
                diff[x][start]++;
                diff[x][end + 1]--;
            }
        }

        int ans = -1;

        // Check every possible number
        for (int x = 0; x <= 50; x++) {

            int current = 0;
            int windows = 0;

            for (int start = 0; start <= n - k; start++) {

                current += diff[x][start];

                if (current > 0) {
                    windows++;
                }
            }

            // x appears in exactly one subarray
            if (windows == 1) {
                ans = x;
            }
        }

        return ans;
    }
}