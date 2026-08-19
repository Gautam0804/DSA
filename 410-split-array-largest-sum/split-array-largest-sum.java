class Solution {
    public int splitArray(int[] nums, int k) {

        long left = 0;
        long right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        long answer = right;

        while (left <= right) {

            long mid = left + (right - left) / 2;

            int parts = 1;
            long currentSum = 0;

            for (int num : nums) {

                if (currentSum + num > mid) {
                    parts++;
                    currentSum = 0;
                }

                currentSum += num;
            }

            if (parts <= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return (int) answer;
    }
}