class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Count characters in s
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check whether a palindrome can be formed
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        // Frequency of characters in the first half
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = freq[i] / 2;
        }

        /*
         * --------------------------------------------------
         * STEP 1:
         * Try to make the first half exactly equal to
         * target's first half.
         * --------------------------------------------------
         */

        int[] remaining = halfCount.clone();

        char[] equalHalf = new char[halfLen];

        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            equalHalf[i] = target.charAt(i);
            remaining[c]--;
        }

        /*
         * If target's first half is possible, build that
         * palindrome and check it.
         */
        if (possible) {

            String candidate =
                    makePalindrome(equalHalf, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * --------------------------------------------------
         * STEP 2:
         *
         * Find the smallest half that is greater than
         * target's first half.
         *
         * We choose a position 'pivot' where the first
         * difference occurs.
         *
         * Everything before pivot is equal to target.
         *
         * At pivot we choose the smallest available
         * character greater than target[pivot].
         *
         * Everything after pivot is filled in ascending order.
         * --------------------------------------------------
         */

        for (int pivot = halfLen - 1;
             pivot >= 0;
             pivot--) {

            int[] count = halfCount.clone();

            // Try to match target[0 ... pivot-1]
            boolean prefixPossible = true;

            for (int i = 0; i < pivot; i++) {

                int c = target.charAt(i) - 'a';

                if (count[c] == 0) {
                    prefixPossible = false;
                    break;
                }

                count[c]--;
            }

            if (!prefixPossible) {
                continue;
            }

            int targetChar =
                    target.charAt(pivot) - 'a';

            /*
             * Choose the smallest available character
             * greater than target[pivot].
             */
            for (int bigger = targetChar + 1;
                 bigger < 26;
                 bigger++) {

                if (count[bigger] == 0) {
                    continue;
                }

                char[] half = new char[halfLen];

                // Copy target prefix
                for (int i = 0; i < pivot; i++) {
                    half[i] = target.charAt(i);
                }

                // First character that makes us greater
                half[pivot] =
                        (char) ('a' + bigger);

                count[bigger]--;

                // Fill remaining positions with
                // the smallest possible characters
                int index = pivot + 1;

                for (int c = 0; c < 26; c++) {

                    while (count[c] > 0) {

                        half[index++] =
                                (char) ('a' + c);

                        count[c]--;
                    }
                }

                return makePalindrome(
                        half,
                        middle,
                        n
                );
            }
        }

        return "";
    }

    private String makePalindrome(
            char[] half,
            int middle,
            int n) {

        StringBuilder result =
                new StringBuilder(n);

        // First half
        for (char c : half) {
            result.append(c);
        }

        // Middle character
        if (n % 2 == 1) {
            result.append(
                    (char) ('a' + middle)
            );
        }

        // Reverse of first half
        for (int i = half.length - 1;
             i >= 0;
             i--) {

            result.append(half[i]);
        }

        return result.toString();
    }
}