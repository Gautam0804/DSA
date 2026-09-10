class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = 0;
        int maxSum = Integer.MIN_VALUE;

        int currentMin = 0;
        int minSum = Integer.MAX_VALUE;

        for (int num : nums) {

            // Total sum
            totalSum += num;

            // Kadane for maximum subarray
            currentMax += num;
            maxSum = Math.max(maxSum, currentMax);

            if (currentMax < 0) {
                currentMax = 0;
            }

            // Kadane for minimum subarray
            currentMin += num;
            minSum = Math.min(minSum, currentMin);

            if (currentMin > 0) {
                currentMin = 0;
            }
        }

        // All elements negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Circular maximum
        int circularSum = totalSum - minSum;

        return Math.max(maxSum, circularSum);
    }
}