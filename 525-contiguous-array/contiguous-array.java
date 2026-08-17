class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int n = nums.length;
        int prefixSum = 0;
        int maxLength = 0;

        for(int i =0;i<n;i++){
           if(nums[i] == 0){
            prefixSum--;
           } else {
            prefixSum++;
           }

            if(map.containsKey(prefixSum)){
                maxLength = Math.max(maxLength, i-map.get(prefixSum));
            } else{
            map.put(prefixSum, i);
            }

        }
        return maxLength;
    }
}