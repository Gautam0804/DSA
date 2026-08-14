class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int n = nums.length;
        int maxLen = 0;
        int sum = 0;

        for(int i =0;i<n;i++){
            if(nums[i] == 0 ){
                sum--;
            } else {
                sum++;
            }
            if(map.containsKey(sum)){
                int length = i-map.get(sum);
                maxLen = Math.max(maxLen,length);
            } else {
                map.put(sum,i);
            }
        }
        return maxLen;
    }
}