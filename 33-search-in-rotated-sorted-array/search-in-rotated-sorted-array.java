class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            // LEFT half is sorted
            if (nums[left] <= nums[mid]) {

                // Target lies inside sorted LEFT half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } 
                else {
                    left = mid + 1;
                }
            }

            // RIGHT half is sorted
            else {

                // Target lies inside sorted RIGHT half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } 
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}