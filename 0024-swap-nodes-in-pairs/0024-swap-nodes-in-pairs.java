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
    public ListNode swapPairs(ListNode head) {
        // Base case: if list is empty or has only one node, no swaps are needed.
        if (head == null || head.next == null) {
            return head;
        }
        
        // Initialize dummy node to safely hold the new head reference
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;
        
        // Ensure there are at least two nodes left to swap
        while (curr != null && curr.next != null) {
            ListNode first = curr;
            ListNode second = curr.next;
            
            // Step 1: Save reference to the next pair ahead [NeetCode]
            ListNode nextPair = second.next;
            
            // Step 2: Swap the current pair pointers
            second.next = first;
            first.next = nextPair;
            prev.next = second;
            
            // Step 3: Advance the pointers for the next iteration
            prev = first;
            curr = nextPair;
        }
        
        return dummy.next;
    }
}
