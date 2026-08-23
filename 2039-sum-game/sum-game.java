class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int half = n / 2;

        int sum = 0;
        int questionDiff = 0;

        for (int i = 0; i < n; i++) {

            if (num.charAt(i) == '?') {

                if (i < half) {
                    questionDiff++;
                } else {
                    questionDiff--;
                }

            } else {

                if (i < half) {
                    sum += num.charAt(i) - '0';
                } else {
                    sum -= num.charAt(i) - '0';
                }
            }
        }

        // Equal number of '?' on both sides
        if (questionDiff == 0) {
            return sum != 0;
        }

        // Odd difference means Alice can force a win
        if (Math.abs(questionDiff) % 2 == 1) {
            return true;
        }

        // Each pair of extra '?' can change the difference by 9
        sum += 9 * (questionDiff / 2);

        return sum != 0;
    }
}