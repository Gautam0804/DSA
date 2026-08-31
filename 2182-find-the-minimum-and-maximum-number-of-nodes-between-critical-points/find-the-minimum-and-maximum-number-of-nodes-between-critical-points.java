class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            ListNode next = curr.next;

            // Check local maxima or local minima
            boolean isCritical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // We already have a previous critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                            minDistance,
                            index - prevCritical
                    );
                }

                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (firstCritical == -1 || firstCritical == prevCritical) {
            return new int[]{-1, -1};
        }

        // Maximum distance
        maxDistance = prevCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}