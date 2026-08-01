/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {

        // If the list has 0 or 1 node, no swap is needed
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {

            ListNode first = prev.next;
            ListNode second = first.next;

            // Swap the pair
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move to the next pair
            prev = first;
        }

        return dummy.next;
    }
}