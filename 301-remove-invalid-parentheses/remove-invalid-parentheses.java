class Solution {
    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String curr = queue.poll();

                if (isValid(curr)) {
                    ans.add(curr);
                    found = true;
                }

                if (found) {
                    continue;
                }

                for (int j = 0; j < curr.length(); j++) {

                
                    if (curr.charAt(j) != '(' &&
                        curr.charAt(j) != ')') {
                        continue;
                    }

                    String next = curr.substring(0, j)
                                  + curr.substring(j + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            if (found) {
                break;
            }
        }

        return ans;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } 
            else if (ch == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }
}