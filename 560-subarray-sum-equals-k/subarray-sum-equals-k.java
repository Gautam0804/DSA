class Solution {
    public int subarraySum(int[] nums, int k) {
        // step 1 find subarray
        // step 2 sum of subarray = k
        // step 3 print no of subarray

        // using HashMap

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k)) {
                total += map.get(sum - k);
            } 
                map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return total;
    }

}