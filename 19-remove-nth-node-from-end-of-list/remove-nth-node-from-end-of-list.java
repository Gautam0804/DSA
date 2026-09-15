class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;

        // Calculate size
        ListNode temp = head;

        while (temp != null) {
            temp = temp.next;
            size++;
        }

        // Remove head if n == size
        if (n == size) {
            return head.next;
        }

        // Find previous node of the node to delete
        int i = 1;
        int iToFind = size - n;

        ListNode prev = head;

        while (i < iToFind) {
            prev = prev.next;
            i++;
        }

        // Remove nth node from end
        prev.next = prev.next.next;

        return head;
    }
}