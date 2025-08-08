package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class ReverseList {
    public static void main(String[] args) {
        int[] head = {1,2,3,4,5};
        ListNode headA = arrayToList(head);
        ListNode res=reverseList(headA);
        System.out.println(res.val);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }
}
