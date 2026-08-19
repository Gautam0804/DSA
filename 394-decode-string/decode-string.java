class Solution {
    public String decodeString(String s) {

        Stack<Integer> numbers = new Stack<>();
        Stack<String> strings = new Stack<>();

        String current = "";
        int number = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            } 
            else if (ch == '[') {

                numbers.push(number);
                strings.push(current);

                number = 0;
                current = "";

            } 
            else if (ch == ']') {

                int count = numbers.pop();
                String previous = strings.pop();

                String temp = "";

                for (int i = 0; i < count; i++) {
                    temp += current;
                }

                current = previous + temp;

            } 
            else {

                current += ch;
            }
        }

        return current;
    }
}