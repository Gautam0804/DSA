class Solution {
    public int[] resultArray(int[] nums) {

        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        int size1 = 0;
        int size2 = 0;

        // First operation
        arr1[size1++] = nums[0];

        // Second operation
        arr2[size2++] = nums[1];

        // Remaining operations
        for (int i = 2; i < n; i++) {

            if (arr1[size1 - 1] > arr2[size2 - 1]) {
                arr1[size1++] = nums[i];
            } else {
                arr2[size2++] = nums[i];
            }
        }

        // Create result
        int[] result = new int[n];

        int index = 0;

        // Add arr1
        for (int i = 0; i < size1; i++) {
            result[index++] = arr1[i];
        }

        // Add arr2
        for (int i = 0; i < size2; i++) {
            result[index++] = arr2[i];
        }

        return result;
    }
}