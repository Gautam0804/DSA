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
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        // Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Move prev to node before left
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // First node of sublist
        ListNode curr = prev.next;

        // Reverse sublist
        for (int i = 0; i < right - left; i++) {

            ListNode nextNode = curr.next;

            curr.next = nextNode.next;

            nextNode.next = prev.next;

            prev.next = nextNode;
        }

        return dummy.next;
    }
}