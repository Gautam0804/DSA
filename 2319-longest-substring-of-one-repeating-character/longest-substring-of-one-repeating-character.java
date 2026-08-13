class Solution {

    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int length;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            best = 1;
            length = 1;
        }

        Node() {
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        int k = queryCharacters.length();

        arr = s.toCharArray();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(arr[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(
            int node,
            int left,
            int right,
            int index,
            char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {

        Node res = new Node();

        res.length = left.length + right.length;

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Prefix
        res.prefix = left.prefix;

        if (left.prefix == left.length &&
            left.rightChar == right.leftChar) {

            res.prefix = left.length + right.prefix;
        }

        // Suffix
        res.suffix = right.suffix;

        if (right.suffix == right.length &&
            left.rightChar == right.leftChar) {

            res.suffix = right.length + left.suffix;
        }

        // Best answer
        res.best = Math.max(left.best, right.best);

        // Join left suffix + right prefix
        if (left.rightChar == right.leftChar) {
            res.best = Math.max(
                res.best,
                left.suffix + right.prefix
            );
        }

        return res;
    }
}