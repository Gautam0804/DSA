class Solution {

    // factorCount[d][p]
    // p = 0 -> factor 2
    // p = 1 -> factor 3
    // p = 2 -> factor 5
    // p = 3 -> factor 7
    static final int[][] FACTOR = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // --------------------------------------------------
        // 1. Factor t into 2, 3, 5, 7
        // --------------------------------------------------

        int[] target = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                target[i]++;
                t /= primes[i];
            }
        }

        // t has some prime factor other than 2,3,5,7
        if (t != 1) {
            return "-1";
        }

        int n = num.length();

        // --------------------------------------------------
        // 2. Convert required prime factors into digits
        // --------------------------------------------------

        int[] requiredDigits = getFactorCount(target);

        // If minimum number of digits itself is longer than num,
        // it is automatically the answer.
        if (sum(requiredDigits) > n) {
            return construct(requiredDigits);
        }

        // --------------------------------------------------
        // 3. Count prime factors in num
        // --------------------------------------------------

        int[] prefix = getPrimeCount(num);

        int firstZero = num.indexOf('0');

        // If there is no zero, num can potentially itself
        // be the answer.
        if (firstZero == -1) {

            firstZero = n;

            if (isSubset(target, prefix)) {
                return num;
            }
        }

        // --------------------------------------------------
        // 4. Try changing one digit
        // --------------------------------------------------

        /*
         * We move from RIGHT to LEFT.
         *
         * Changing a digit farther to the right produces
         * a smaller number.
         */
        for (int i = n - 1; i >= 0; i--) {

            int current = num.charAt(i) - '0';

            // Remove current digit from suffix factor count.
            for (int p = 0; p < 4; p++) {
                prefix[p] -= FACTOR[current][p];

                if (prefix[p] < 0) {
                    prefix[p] = 0;
                }
            }

            /*
             * If i is after the first zero, the unchanged prefix
             * already contains a zero, so this position cannot
             * produce a zero-free answer.
             */
            if (i > firstZero) {
                continue;
            }

            // Try every larger digit.
            for (int bigger = current + 1; bigger <= 9; bigger++) {

                /*
                 * Required factors after:
                 *
                 * prefix + bigger digit
                 */
                int[] remaining = new int[4];

                for (int p = 0; p < 4; p++) {

                    remaining[p] = Math.max(
                        0,
                        target[p]
                            - prefix[p]
                            - FACTOR[bigger][p]
                    );
                }

                /*
                 * Convert remaining factors into the minimum
                 * number of digits required.
                 */
                int[] neededDigits = getFactorCount(remaining);

                int needed = sum(neededDigits);

                int space = n - 1 - i;

                if (needed <= space) {

                    /*
                     * Fill unused positions with '1'.
                     *
                     * Then append the required digits in sorted
                     * order.
                     */
                    StringBuilder ans = new StringBuilder();

                    // Keep prefix
                    ans.append(num, 0, i);

                    // Make this digit larger
                    ans.append((char) ('0' + bigger));

                    // Smallest unused digits are 1s
                    ans.append("1".repeat(space - needed));

                    // Required digits
                    ans.append(construct(neededDigits));

                    return ans.toString();
                }
            }
        }

        // --------------------------------------------------
        // 5. Same length impossible
        // --------------------------------------------------

        int[] allRequired = getFactorCount(target);

        int required = sum(allRequired);

        /*
         * Any number with n+1 digits is greater than num.
         *
         * Put the required digits at the end and fill the
         * remaining positions with 1.
         */
        int ones = n + 1 - required;

        return "1".repeat(ones) + construct(allRequired);
    }

    // ------------------------------------------------------
    // Factor t -> minimum number of digits
    // ------------------------------------------------------

    private int[] getFactorCount(int[] count) {

        int[] res = new int[10];

        // 2^3 = 8
        res[8] = count[0] / 3;

        int remaining2 = count[0] % 3;

        // 3^2 = 9
        res[9] = count[1] / 2;

        int remaining3 = count[1] % 2;

        // 2^2 = 4
        res[4] = remaining2 / 2;

        int remaining2After4 = remaining2 % 2;

        // Combine one 2 and one 3 -> 6
        if (remaining2After4 == 1 && remaining3 == 1) {

            res[6] = 1;

            remaining2After4 = 0;
            remaining3 = 0;
        }

        /*
         * Special case:
         *
         * one 3 + one 4
         *
         * can be converted into:
         *
         * 2 + 6
         *
         * because:
         *
         * 3 * 4 = 12
         * 2 * 6 = 12
         */
        if (remaining3 == 1 && res[4] > 0) {

            res[4]--;

            res[2]++;
            res[6]++;

            remaining3 = 0;
        }

        // Remaining single 2
        if (remaining2After4 == 1) {
            res[2]++;
        }

        // Remaining single 3
        if (remaining3 == 1) {
            res[3]++;
        }

        // 5 and 7 cannot combine with anything
        res[5] = count[2];
        res[7] = count[3];

        return res;
    }

    // ------------------------------------------------------
    // Count prime factors in a string
    // ------------------------------------------------------

    private int[] getPrimeCount(String s) {

        int[] count = new int[4];

        for (int i = 0; i < s.length(); i++) {

            int d = s.charAt(i) - '0';

            for (int p = 0; p < 4; p++) {
                count[p] += FACTOR[d][p];
            }
        }

        return count;
    }

    // ------------------------------------------------------
    // Is a subset?
    // ------------------------------------------------------

    private boolean isSubset(int[] need, int[] have) {

        for (int i = 0; i < 4; i++) {

            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }

    // ------------------------------------------------------
    // Sum of digit counts
    // ------------------------------------------------------

    private int sum(int[] count) {

        int ans = 0;

        for (int x : count) {
            ans += x;
        }

        return ans;
    }

    // ------------------------------------------------------
    // Construct digits in sorted order
    // ------------------------------------------------------

    private String construct(int[] count) {

        StringBuilder sb = new StringBuilder();

        for (int digit = 2; digit <= 9; digit++) {

            for (int j = 0; j < count[digit]; j++) {
                sb.append((char) ('0' + digit));
            }
        }

        return sb.toString();
    }
}