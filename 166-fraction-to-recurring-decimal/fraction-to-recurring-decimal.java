class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0) {
            return "0";
        }

        StringBuilder ans = new StringBuilder();

        if ((numerator < 0) ^ (denominator < 0)) {
            ans.append("-");
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        ans.append(num / den);

        long remainder = num % den;

        if (remainder == 0) {
            return ans.toString();
        }

        ans.append(".");

        HashMap<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {

            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                ans.insert(index, "(");
                ans.append(")");
                break;
            }

            map.put(remainder, ans.length());

            remainder *= 10;

            ans.append(remainder / den);

            remainder %= den;
        }

        return ans.toString();
    }
}