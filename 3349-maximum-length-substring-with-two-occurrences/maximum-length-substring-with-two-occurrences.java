class Solution {
    public int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            int current = s.charAt(right) - 'a';
            freq[current]++;

            // Shrink window while current window is invalid
            while (freq[current] > 2) {

                int leftChar = s.charAt(left) - 'a';
                freq[leftChar]--;

                left++;
            }

            // Current window is valid
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}