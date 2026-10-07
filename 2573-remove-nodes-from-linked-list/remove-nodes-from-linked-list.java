class Solution {
    public ListNode removeNodes(ListNode head) {

        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // First process the right side
        head.next = removeNodes(head.next);

        // If right side has a greater value
        if (head.val < head.next.val) {
            return head.next;
        }

        // Otherwise keep current node
        return head;
    }
}