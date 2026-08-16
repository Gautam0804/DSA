class NumArray {

    int[] ans;

    public NumArray(int[] nums) {
        int n = nums.length;
        int sum = 0;
        ans = new int[n];

        for(int i=0;i<n;i++){
            sum += nums[i];
            ans[i] = sum;
        }
        
    }
    
    public int sumRange(int left, int right) {
        int answer = 0;
        
        if(left == 0){
             answer = ans[right];
        } else {
            answer = ans[right]-ans[left-1];
        }
        return answer;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */