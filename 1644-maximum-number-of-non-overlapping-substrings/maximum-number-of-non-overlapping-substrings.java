import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Find all valid intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == n) {
                continue;
            }

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {

                int ch = s.charAt(i) - 'a';

                if (first[ch] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Step 3: Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        // Step 4: Greedy selection
        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > prevEnd) {

                result.add(s.substring(l, r + 1));

                prevEnd = r;
            }
        }

        return result;
    }
}