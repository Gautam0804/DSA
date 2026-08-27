class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        // Count characters of s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        String answer = "";

        // Try every position as the position where
        // our answer becomes greater than target.
        for (int i = 0; i < n; i++) {

            int[] temp = freq.clone();

            // Match target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (temp[c] == 0) {
                    possible = false;
                    break;
                }

                temp[c]--;
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest
            // character greater than target[i]
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (temp[c] > 0) {

                    StringBuilder result = new StringBuilder();

                    // Prefix equal to target
                    result.append(target, 0, i);

                    // Make current position greater
                    result.append((char) ('a' + c));

                    temp[c]--;

                    // Put remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (temp[x] > 0) {
                            result.append((char) ('a' + x));
                            temp[x]--;
                        }
                    }

                    String candidate = result.toString();

                    // Keep the lexicographically smallest answer
                    if (answer.equals("") || candidate.compareTo(answer) < 0) {
                        answer = candidate;
                    }

                    // For this position, c is already the
                    // smallest possible greater character.
                    break;
                }
            }
        }

        return answer;
    }
}