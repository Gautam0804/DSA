class Solution {

    public ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public boolean isPalindrome(ListNode head) {

        // Empty list or single node
        if (head == null || head.next == null) {
            return true;
        }

        // Find middle
        ListNode midNode = findMid(head);

        // Reverse second half
        ListNode prev = null;
        ListNode curr = midNode;

        while (curr != null) {
            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode right = prev;
        ListNode left = head;

        // Compare both halves
        while (right != null) {

            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }
}