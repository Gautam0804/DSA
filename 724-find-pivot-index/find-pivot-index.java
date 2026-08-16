class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
       int totalSum = 0;

       for(int i=0;i<n;i++){
        totalSum += nums[i]; 
       }

       int leftSum = 0;

        for(int i=0;i<n;i++){
            int rightSum = totalSum-leftSum-nums[i];

            if(leftSum == rightSum){
                return i;
            } else{
                leftSum += nums[i];
            }
        }
        return -1;
    }
}