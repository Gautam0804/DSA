class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        // Total flowers required
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int low = bloomDay[0];
        int high = bloomDay[0];

        // Minimum and maximum bloom day
        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int flowers = 0;
            int bouquets = 0;

            // Check: mid days mein kitne bouquets banenge?
            for (int day : bloomDay) {

                if (day <= mid) {
                    flowers++;

                    // k consecutive flowers mil gaye
                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                    }

                } else {
                    // Consecutive sequence break
                    flowers = 0;
                }
            }

            if (bouquets >= m) {
                // mid days enough hain
                // aur minimum days try karo
                high = mid - 1;
            } else {
                // mid days enough nahi hain
                low = mid + 1;
            }
        }

        return low;
    }
}