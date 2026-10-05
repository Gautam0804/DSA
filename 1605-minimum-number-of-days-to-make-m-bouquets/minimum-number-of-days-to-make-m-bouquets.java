class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        long need = (long) m * k;

        if (need > bloomDay.length) {
            return -1;
        }

        int left = 1;
        int right = 0;

        for (int day : bloomDay) {
            right = Math.max(right, day);
        }

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (can(bloomDay, m, k, mid)) {
                answer = mid;
                right = mid - 1;   
            } else {
                left = mid + 1;   
            }
        }

        return answer;
    }

    private boolean can(int[] bloomDay, int m, int k, int day) {

        int flowers = 0;
        int bouquets = 0;

        for (int bloom : bloomDay) {

            if (bloom <= day) {
                flowers++;

                if (flowers == k) {
                    bouquets++;
                    flowers = 0;

                    if (bouquets == m) {
                        return true;
                    }
                }

            } else {
                flowers = 0;
            }
        }

        return false;
    }
}