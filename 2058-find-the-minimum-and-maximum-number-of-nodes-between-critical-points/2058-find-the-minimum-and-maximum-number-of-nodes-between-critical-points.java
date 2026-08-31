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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // We need at least 3 nodes to have any critical points
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        int currentIndex = 1;
        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            // Check if curr is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > curr.next.val;
            boolean isMinima = curr.val < prev.val && curr.val < curr.next.val;

            if (isMaxima || isMinima) {
                // If it's the very first critical point found
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update the minimum distance between consecutive critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                // Track the latest critical point index
                prevCriticalIndex = currentIndex;
            }

            // Move pointers forward
            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // If less than 2 critical points were found
        if (firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        // Maximum distance is always between the first and last critical points
        int maxDistance = prevCriticalIndex - firstCriticalIndex;

        return new int[]{minDistance, maxDistance};
    }
}
