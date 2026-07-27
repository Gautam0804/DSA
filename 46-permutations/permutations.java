class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        helper(nums, 0);
        return ans;
    }

    private void helper(int[] nums, int index) {

        if (index == nums.length) {

            List<Integer> list = new ArrayList<>();

            for (int num : nums) {
                list.add(num);
            }

            ans.add(list);
            return;
        }

        for (int i = index; i < nums.length; i++) {

            swap(nums, index, i);

            helper(nums, index + 1);

            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}