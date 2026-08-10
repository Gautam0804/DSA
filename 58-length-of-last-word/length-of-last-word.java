class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;

        while (i >= 0) {

            // handle the spaces
            if (s.charAt(i) == ' ') {
                if (length == 0) {
                    i--;
                } else {
                    break;
                }
            } else {
                length++;
                i--;
            }
        }
        return length;
    }
}