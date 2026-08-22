class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0;
        int product = 1;
        int orginal = n;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n = n / 10;
        }
        return orginal%(sum+product) == 0;
    }
}