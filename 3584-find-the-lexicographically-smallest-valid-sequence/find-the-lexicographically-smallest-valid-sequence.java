class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // suf[i] = first position in word2 that still
        // needs to be matched using word1[i...]
        int[] suf = new int[n + 1];

        suf[n] = m;

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            suf[i] = j + 1;
        }

        int[] ans = new int[m];

        int j2 = 0;
        boolean changed = false;
        int count = 0;

        for (int i = 0; i < n && j2 < m; i++) {

            // Normal matching
            if (word1.charAt(i) == word2.charAt(j2)) {

                ans[count++] = i;
                j2++;

            }
            // Use the one allowed change
            else if (!changed && suf[i + 1] <= j2 + 1) {

                ans[count++] = i;
                j2++;
                changed = true;
            }
        }

        // Could not form a valid sequence
        if (count != m) {
            return new int[0];
        }

        return ans;
    }
}