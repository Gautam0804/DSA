class Solution {

    static class Node {
        int[] pref;
        int[] suff;
        int[] all;
        int prod;

        Node(int k) {
            pref = new int[k];
            suff = new int[k];
            all = new int[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);

        // Product of the complete segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes
        // Prefix lies completely in left
        for (int r = 0; r < k; r++) {
            res.pref[r] = left.pref[r];
        }

        // Prefix = entire left + prefix of right
        for (int r = 0; r < k; r++) {
            if (right.pref[r] == 0) continue;

            int rem = (left.prod * r) % k;
            res.pref[rem] += right.pref[r];
        }

        // Suffixes
        // Suffix lies completely in right
        for (int r = 0; r < k; r++) {
            res.suff[r] = right.suff[r];
        }

        // Suffix = suffix of left + entire right
        for (int r = 0; r < k; r++) {
            if (left.suff[r] == 0) continue;

            int rem = (r * right.prod) % k;
            res.suff[rem] += left.suff[r];
        }

        // Subarrays completely inside left
        for (int r = 0; r < k; r++) {
            res.all[r] += left.all[r];
        }

        // Subarrays completely inside right
        for (int r = 0; r < k; r++) {
            res.all[r] += right.all[r];
        }

        // Subarrays crossing left and right
        for (int a = 0; a < k; a++) {
            if (left.suff[a] == 0) continue;

            for (int b = 0; b < k; b++) {
                if (right.pref[b] == 0) continue;

                int rem = (a * b) % k;

                res.all[rem] += left.suff[a] * right.pref[b];
            }
        }

        return res;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            tree[node].suff[rem] = 1;
            tree[node].all[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {

        if (l == r) {
            nums[index] = value;

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            tree[node].suff[rem] = 1;
            tree[node].all[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        // Build segment tree
        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists for future queries
            update(1, 0, n - 1, index, value);

            // Get information for nums[start ... n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // pref[x] = number of valid remaining arrays
            result[i] = res.pref[x];
        }

        return result;
    }
}