class Solution {

    public double myPow(double x, int n) {

        long N = n;

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return power(x, N);
    }

    private double power(double x, long n) {

        if (n == 0) {
            return 1;
        }

        double half = power(x, n / 2);
        double ans = half * half;

        // If n is odd
        if (n % 2 == 1) {
            ans = ans * x;
        }

        return ans;
    }
}