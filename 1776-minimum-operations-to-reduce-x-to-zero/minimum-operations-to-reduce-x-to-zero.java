class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        long total = 0;

        for (int num : nums) {
            total += num;
        }

        long target = total - x;

        // x is greater than total sum
        if (target < 0) {
            return -1;
        }

        // Need to remove all elements
        if (target == 0) {
            return n;
        }

        int left = 0;
        long windowSum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {

            windowSum += nums[right];

            while (windowSum > target && left <= right) {
                windowSum -= nums[left];
                left++;
            }

            if (windowSum == target) {
                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );
            }
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}