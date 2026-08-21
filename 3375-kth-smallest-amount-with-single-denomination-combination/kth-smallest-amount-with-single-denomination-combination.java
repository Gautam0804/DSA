class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long left = 1;
        long right = (long) getMin(coins) * k;

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(coins, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(int[] coins, long x) {

        int n = coins.length;
        long result = 0;

        // All non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // This subset contributes 0
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long contribution = x / lcm;

            // Odd number of coins -> add
            // Even number of coins -> subtract
            if (bits % 2 == 1) {
                result += contribution;
            } else {
                result -= contribution;
            }
        }

        return result;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private int getMin(int[] coins) {

        int min = coins[0];

        for (int coin : coins) {
            min = Math.min(min, coin);
        }

        return min;
    }
}