class Solution {
    public String longestCommonPrefix(String[] strs) {

        // Start with the first string as our prefix
        String prefix = strs[0];

        // Compare prefix with every other string
        for (int i = 1; i < strs.length; i++) {

            // Keep reducing prefix until the current
            // string starts with the prefix
            while (!strs[i].startsWith(prefix)) {

                // Remove the last character
                prefix = prefix.substring(0, prefix.length() - 1);

                // If nothing is common
                if (prefix.length() == 0) {
                    return "";
                }
            }
        }

        return prefix;
    }
}