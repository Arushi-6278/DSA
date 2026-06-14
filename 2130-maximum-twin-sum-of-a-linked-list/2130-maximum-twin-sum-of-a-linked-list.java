class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        
        int maxVal = 0;
        ListNode firstHalf = head;
        ListNode secondHalf = prev;
        
        while (secondHalf != null) {
            maxVal = Math.max(maxVal, firstHalf.val + secondHalf.val);
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return maxVal;
    }
}