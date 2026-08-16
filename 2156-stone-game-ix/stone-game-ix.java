class Solution {
    public boolean stoneGameIX(int[] stones) {
        int zero = 0;
        int one = 0;
        int two = 0;

        // count remainder

        for(int stone:stones){
            if(stone%3 == 0){
                zero++;
            } else if(stone%3 == 1){
                one++;
            } else {
                two++;
            }
        }
        // No remainder 1 or 2 stones

        if(one == 0 && two == 0){
            return false;
        }
        int max = Math.max(one,two);
        int min = Math.min(one,two);

        // Even number of 0 remainder-0 stones
        if(zero%2 == 0){
            return min != 0;
        }
        // odd number of remainder-0 stones
        return max-2>min;
        
    }
}