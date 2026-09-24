class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
       
        for(int i=0;i<n;i++){
            int num = nums[i];
             int sum = 0;

            while(num>0){
                sum += num%10;
                num /= 10;
            }
            if(sum == i){
                return i;
            }
        }
        return -1;
    }
}