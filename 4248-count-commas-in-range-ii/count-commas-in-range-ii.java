class Solution {
    public long countCommas(long n) {
        long ans = 0;

        long divisor = 1000;

        while(divisor <= n){
            ans += n-divisor+1;
            divisor *= 1000;
        }
        return ans;
    }
}