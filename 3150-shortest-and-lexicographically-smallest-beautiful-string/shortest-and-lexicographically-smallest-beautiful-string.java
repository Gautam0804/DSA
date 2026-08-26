class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        // Store positions of all 1's
        ArrayList<Integer> ones = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        // If total number of 1's is less than k,
        // no beautiful substring exists
        if (ones.size() < k) {
            return "";
        }

        String ans = "";
        int minLen = Integer.MAX_VALUE;

        // Consider every group of k consecutive 1's
        for (int i = 0; i + k - 1 < ones.size(); i++) {

            int start = ones.get(i);
            int end = ones.get(i + k - 1);

            int len = end - start + 1;

            // Current substring
            String curr = s.substring(start, end + 1);

            // Case 1: Found a shorter substring
            if (len < minLen) {
                minLen = len;
                ans = curr;
            }

            // Case 2: Same length, choose lexicographically smaller
            else if (len == minLen && curr.compareTo(ans) < 0) {
                ans = curr;
            }
        }

        return ans;
    }
}