class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        // Put all elements of nums1 into set
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        // Check elements of nums2
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }

        // Convert Set<Integer> to int[]
        return result.stream()
                     .mapToInt(Integer::intValue)
                     .toArray();
    }
}