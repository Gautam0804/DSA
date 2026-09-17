class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        int INF = Integer.MAX_VALUE;

        int[] best = new int[n];

        int left = 0;
        int sum = 0;

        int ans = INF;

        for (int right = 0; right < n; right++) {

            // Add current element
            sum += arr[right];

            // Shrink window if sum exceeds target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // If current window sum equals target
            if (sum == target) {

                int len = right - left + 1;

                // Check previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {

                    ans = Math.min(
                        ans,
                        len + best[left - 1]
                    );
                }

                // Update best array
                if (right == 0) {
                    best[right] = len;
                } else {
                    best[right] = Math.min(
                        best[right - 1],
                        len
                    );
                }

            } else {

                // Carry previous minimum
                if (right == 0) {
                    best[right] = INF;
                } else {
                    best[right] = best[right - 1];
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
}