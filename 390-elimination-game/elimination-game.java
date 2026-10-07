class Solution {

    public int lastRemaining(int n) {
        return solve(n, true);
    }

    private int solve(int n, boolean left) {


        if (n == 1) {
            return 1;
        }

        if (left) {
            return 2 * solve(n / 2, false);
        }


        if (n % 2 == 1) {
            return 2 * solve(n / 2, true);
        }

        return 2 * solve(n / 2, true) - 1;
    }
}