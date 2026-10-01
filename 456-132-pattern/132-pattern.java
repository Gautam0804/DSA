class Solution {
    public boolean find132pattern(int[] nums) {
        int third = Integer.MIN_VALUE; // Candidate for the "2" in 132
        int[] stack = new int[nums.length];
        int top = -1;

        // Scan from right to left.
        for (int i = nums.length - 1; i >= 0; i--) {
            // nums[i] is the "1"; third is the "2".
            if (nums[i] < third) {
                return true;
            }

            // A larger value becomes the best "2" candidate.
            while (top >= 0 && nums[i] > stack[top]) {
                third = stack[top--];
            }

            stack[++top] = nums[i];
        }

        return false;
    }
}