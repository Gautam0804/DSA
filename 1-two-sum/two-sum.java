import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map  = new HashMap<>();
        int n = nums.length;
        int sum = 0;

        for(int i=0;i<n;i++){
         sum = target-nums[i];

            if(map.containsKey(sum)){
              return new int[]{map.get(sum),i};

            }
            map.put(nums[i],i);
        }
        return new int[] {};
        
    }
}