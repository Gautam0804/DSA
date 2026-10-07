class Solution {
    public char kthCharacter(int k) {

        StringBuilder word = new StringBuilder("a");

        while (word.length() < k) {

            int size = word.length();

            for (int i = 0; i < size; i++) {

                char ch = word.charAt(i);

                if (ch == 'z') {
                    ch = 'a';
                } else {
                    ch++;
                }

                word.append(ch);
            }
        }

        return word.charAt(k - 1);
    }
}