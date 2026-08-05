class Solution {
    public void sortColors(int[] nums) {

        // int n = nums.length;

        // for(int i = 0; i<n-1; i++){
        //     for(int j = i+1;j<n;j++){

        //         // swap
        //         if(nums[i]>nums[j]){
        //             int temp = nums[i];
        //             nums[i] = nums[j];
        //             nums[j] = temp;
        //         }
        //     }
        // }

         int zero = 0;
        int one = 0;
        int two = 0;

        for (int num : nums) {
            if (num == 0)
                zero++;
            else if (num == 1)
                one++;
            else
                two++;
        }

        int index = 0;

        while (zero-- > 0)
            nums[index++] = 0;

        while (one-- > 0)
            nums[index++] = 1;

        while (two-- > 0)
            nums[index++] = 2;


        
    }
}