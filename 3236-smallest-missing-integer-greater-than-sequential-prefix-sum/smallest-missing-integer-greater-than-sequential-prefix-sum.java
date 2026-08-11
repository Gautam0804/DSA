import java.util.HashSet;

class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;

        HashSet<Integer> set = new HashSet<>();

        // Store all elements in the set
        for (int num : nums) {
            set.add(num);
        }

        // Find the sum of the longest sequential prefix
        int seqSum = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                seqSum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest integer >= seqSum
        // that is not present in nums
        while (set.contains(seqSum)) {
            seqSum++;
        }

        return seqSum;
    }
}