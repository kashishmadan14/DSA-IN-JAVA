/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node to seamlessly handle edge cases like removing the head
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode cur = head;
        
        while (cur != null) {
            // Check if the current node has a duplicate neighbor
            if (cur.next != null && cur.val == cur.next.val) {
                // Move cur forward to the last node of the duplicate sequence
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // Skip all the duplicates by linking prev's next to the node after cur
                prev.next = cur.next;
            } else {
                // No duplicate detected; safely move the prev pointer forward
                prev = prev.next;
            }
            // Move cur forward for the next iteration
            cur = cur.next;
        }
        
        return dummy.next;
    }
}
