class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

            // Step 1: Store value and original index
        ArrayList<int[]> arr = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            arr.add(new int[]{nums[i], i});
        }

        // Step 2: Sort according to value
        arr.sort((a, b) -> Integer.compare(a[0], b[0]));

        // Step 3: Process each group
        int start = 0;

        while (start < arr.size()) {

            // Find the end of current group
            int end = start;

            while (end + 1 < arr.size()
                    && arr.get(end + 1)[0] - arr.get(end)[0] <= limit) {
                end++;
            }

            // Get original indices of this group
            ArrayList<Integer> indices = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(arr.get(i)[1]);
            }

            // Sort original indices
            Collections.sort(indices);

            // Put sorted values into sorted indices
            for (int i = start; i <= end; i++) {
                nums[indices.get(i - start)] = arr.get(i)[0];
            }

            // Move to next group
            start = end + 1;
        }

        return nums;

    }
}