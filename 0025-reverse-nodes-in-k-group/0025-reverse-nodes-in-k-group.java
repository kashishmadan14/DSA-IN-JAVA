
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
  
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode groupPrev = dummy;
        
        while (true) {
            
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) {
                break; 
            }
            

            ListNode groupNext = kth.next;
           
            ListNode prev = groupNext; 
            ListNode curr = groupPrev.next;
            
            while (curr != groupNext) {
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }
            
            ListNode tmp = groupPrev.next; 
            groupPrev.next = kth;          
            groupPrev = tmp;               
        }
        
        return dummy.next;
    }
    
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
