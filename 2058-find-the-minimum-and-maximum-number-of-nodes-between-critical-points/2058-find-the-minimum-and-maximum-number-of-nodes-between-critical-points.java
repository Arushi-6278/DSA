class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;
        
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) || 
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCriticalIndex);
                }
                prevCriticalIndex = index;
            }
            
            prev = curr;
            curr = curr.next;
            index++;
        }

        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, prevCriticalIndex - firstCriticalIndex};
    }
}