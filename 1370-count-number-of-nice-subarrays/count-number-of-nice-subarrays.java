class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int n = nums.length;
        int sum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] % 2 != 0) {
                sum++;
            }
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;

    }
}