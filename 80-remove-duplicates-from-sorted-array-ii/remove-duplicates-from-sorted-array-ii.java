class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length <= 2) {
            return nums.length;
        }

        int write = 2;

        for (int i = 2; i < nums.length; i++) {

            if (nums[i] != nums[write - 2]) {
                nums[write] = nums[i];
                write++;
            }
        }

        return write;
    }
}