package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class SwapPairs {
    public static void main(String[] args) {
        int[] head = {1, 2, 3, 4};
        ListNode headA = arrayToList(head);
        System.out.println(swapPairs(headA));
    }

    private static ListNode swapPairs(ListNode head) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode temp = hair;
        while (temp.next != null && temp.next.next != null) {
            ListNode listNode1 = temp.next;
            ListNode listNode2 = temp.next.next;
            temp.next = listNode2;
            listNode1.next = listNode2.next;
            listNode2.next = listNode1;
            temp = listNode1;
        }
        return hair.next;
    }
}
