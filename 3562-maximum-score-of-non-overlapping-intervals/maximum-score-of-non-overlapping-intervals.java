import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr[i] = new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            );
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) {
                return Integer.compare(a.l, b.l);
            }
            return Integer.compare(a.r, b.r);
        });

        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i].l;
        }

        // next[i] = first interval with start > arr[i].r
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i].r);
        }

        /*
         * dp[i][k] = best answer using intervals from i onward,
         * selecting at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                State skip = dp[i + 1][k];

                // Option 2: Take current interval
                State takeNext = dp[next[i]][k - 1];

                int[] takeIndices = insertSorted(
                    takeNext.indices,
                    arr[i].idx
                );

                State take = new State(
                    takeNext.score + arr[i].w,
                    takeIndices
                );

                dp[i][k] = better(take, skip);
            }

            dp[i][0] = new State(0, new int[0]);
        }

        return dp[0][4].indices;
    }

    // First position where arr[pos] > target
    private int upperBound(int[] arr, int target) {

        int l = 0;
        int r = arr.length;

        while (l < r) {

            int mid = l + (r - l) / 2;

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    // Insert index into sorted array
    private int[] insertSorted(int[] arr, int value) {

        int[] result = new int[arr.length + 1];

        int i = 0;
        int j = 0;

        while (i < arr.length && arr[i] < value) {
            result[j++] = arr[i++];
        }

        result[j++] = value;

        while (i < arr.length) {
            result[j++] = arr[i++];
        }

        return result;
    }

    // Return the better state
    private State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score: lexicographically smaller indices
        return compare(a.indices, b.indices) < 0 ? a : b;
    }

    private int compare(int[] a, int[] b) {

        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {

            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}