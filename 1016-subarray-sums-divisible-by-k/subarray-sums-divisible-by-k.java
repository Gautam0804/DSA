class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int count = 0;
        int sum = 0;
        map.put(0, 1);

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            int remainder = sum % k;

            if (remainder < 0) {
                remainder = remainder + k;
            }
            if (map.containsKey(remainder)) {
                count = count + map.get(remainder);
            }
            if (map.containsKey(remainder)) {
                int frequency = map.get(remainder);
                map.put(remainder, frequency + 1);
            } else {
                map.put(remainder, 1);
            }

        }
        return count;

    }
}