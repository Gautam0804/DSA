class Solution {
    public String removeDuplicates(String s, int k) {
        // Each entry stores a character and its consecutive count.
        int[][] stack = new int[s.length()][2];
        int top = -1;

        for (char c : s.toCharArray()) {
            if (top >= 0 && stack[top][0] == c) {
                stack[top][1]++;
            } else {
                stack[++top][0] = c;
                stack[top][1] = 1;
            }

            if (stack[top][1] == k) {
                top--;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            result.append(String.valueOf((char) stack[i][0]).repeat(stack[i][1]));
        }

        return result.toString();
    }
}