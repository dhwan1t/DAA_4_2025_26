package DAA.Session_7;

public class LC_141 {
    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public static class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            boolean isCircular = false;

            while(fast != null && fast.next != null){
                fast = fast.next.next;
                slow = slow.next;

                if(fast == slow){
                    isCircular = true;
                    return isCircular;
                }
            }
            return isCircular;
        }
    }
}
