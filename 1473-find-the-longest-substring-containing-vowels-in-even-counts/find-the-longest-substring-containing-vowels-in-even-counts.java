class Solution {
    public int findTheLongestSubstring(String s) {

        HashMap<String, Integer> map = new HashMap<>();

        int[] parity = new int[5];

        // All vowels have appeared 0 times
        map.put("00000", -1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // Toggle vowel parity
            if (c == 'a') {
                parity[0] = 1 - parity[0];
            } 
            else if (c == 'e') {
                parity[1] = 1 - parity[1];
            } 
            else if (c == 'i') {
                parity[2] = 1 - parity[2];
            } 
            else if (c == 'o') {
                parity[3] = 1 - parity[3];
            } 
            else if (c == 'u') {
                parity[4] = 1 - parity[4];
            }

            // Create current state
            String state =
                    "" + parity[0]
                    + parity[1]
                    + parity[2]
                    + parity[3]
                    + parity[4];

            // LOOK
            if (map.containsKey(state)) {

                maxLength = Math.max(
                        maxLength,
                        i - map.get(state)
                );

            } else {

                // STORE earliest index
                map.put(state, i);
            }
        }

        return maxLength;
    }
}