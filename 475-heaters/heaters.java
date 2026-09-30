class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int low = 0;
        int high = Math.max(houses[houses.length - 1],
                            heaters[heaters.length - 1]);

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (warmed(houses, heaters, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean warmed(int[] houses, int[] heaters, int radius) {
        int i = 0;
        int j = 0;

        while (i < houses.length && j < heaters.length) {

            if (houses[i] < heaters[j] - radius) {
                // House is too far to the left
                return false;
            }

            if (houses[i] > heaters[j] + radius) {
                // Current heater is too far left
                j++;
            } else {
                // House is covered
                i++;
            }
        }

        return i == houses.length;
    }
}