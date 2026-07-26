class Solution {
    public int maxProduct(int n) {
        int max1 = 0;
        int max2 = 0;

        while(n>0){
            int ans = n%10;
            n /= 10;

            if(ans>=max1){
                max2 = max1;
                max1 = ans;
            } else if(ans>max2){
                max2 = ans;
            }
        }
        return max1*max2;
        
    }
}