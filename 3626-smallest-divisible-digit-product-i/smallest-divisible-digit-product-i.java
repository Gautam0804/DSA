class Solution {
    public int smallestNumber(int n, int t) {

        while (true) {

            int temp = n;
            int product = 1;

            while (temp > 0) {

                int digit = temp % 10;

                if (digit == 0) {
                    product = 0;
                    break;
                }

                product *= digit;
                temp /= 10;
            }

            if (product % t == 0) {
                return n;
            }

            n++;
        }
    }
}