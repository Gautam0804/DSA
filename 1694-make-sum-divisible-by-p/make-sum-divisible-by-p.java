class Solution {
    public int minSubarray(int[] nums, int p) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        long total = 0;

        // Step 1: Calculate total sum
        for (int num : nums) {
            total += num;
        }

        // Step 2: Remainder that needs to be removed
        int target = (int)(total % p);

        // Already divisible
        if (target == 0) {
            return 0;
        }

        long prefix = 0;
        int minLength = nums.length;

        // Step 3: Find shortest subarray
        for (int i = 0; i < nums.length; i++) {

            prefix += nums[i];

            int remainder = (int)(prefix % p);

            int needed = (remainder - target + p) % p;

            if (map.containsKey(needed)) {
                minLength = Math.min(
                    minLength,
                    i - map.get(needed)
                );
            }

            // Store latest index
            map.put(remainder, i);
        }

        return minLength == nums.length ? -1 : minLength;
    }
}