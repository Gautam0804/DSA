class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // If we have more than k ones,
            // move left until we have k again
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Exactly k ones
            if (ones == k) {

                // Remove leading zeros to make
                // the substring as short as possible
                while (s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;
                String curr = s.substring(left, right + 1);

                // Shorter substring
                if (len < minLen) {
                    minLen = len;
                    ans = curr;
                }

                // Same length -> lexicographically smaller
                else if (len == minLen &&
                         curr.compareTo(ans) < 0) {
                    ans = curr;
                }
            }
        }

        return ans;
    }
}