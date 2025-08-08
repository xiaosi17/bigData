package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class MergeTwoLists {
    public static void main(String[] args) {
        int[] head = {1, 2, 4};
        ListNode headA = arrayToList(head);
        int[] head1 = {1, 3, 4};
        ListNode headB = arrayToList(head1);
        System.out.println(mergeTwoLists(headA, headB));
    }

    private static ListNode mergeTwoLists(ListNode headA, ListNode headB) {
        ListNode ress = new ListNode(-1);
        ListNode res = ress;
        while (headA != null && headB != null) {
            if (headA.val > headB.val) {
                res.next = headB;
                headB = headB.next;
            } else {
                res.next = headA;
                headA = headA.next;
            }
            res = res.next;
        }
        res.next = headA != null ? headA : headB;
        return ress.next;
    }
}
