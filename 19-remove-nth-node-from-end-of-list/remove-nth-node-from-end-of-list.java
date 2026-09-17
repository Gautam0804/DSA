class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Keep gap of n nodes between slow and fast
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Delete the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }
}