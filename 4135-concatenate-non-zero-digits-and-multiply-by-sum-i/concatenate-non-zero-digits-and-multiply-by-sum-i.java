class Solution {
    public long sumAndMultiply(int n) {

        int x = 0;
        int sum = 0;
        int place = 1;

        while (n > 0) {

            int digit = n % 10;

            if (digit != 0) {
                sum += digit;
                x += digit * place;
                place *= 10;
            }

            n /= 10;
        }

        return 1L * x * sum;
    }
}