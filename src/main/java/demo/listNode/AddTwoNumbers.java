package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] l1 = {2, 4, 3};
        ListNode headA = arrayToList(l1);
        int[] l2 = {5, 6, 4};
        ListNode headB = arrayToList(l2);
        System.out.println(addTwoNumbers(headA, headB));
    }

    private static ListNode addTwoNumbers(ListNode headA, ListNode headB) {

        ListNode res = null, temp = null;
        int carry = 0;
        while (headA != null || headB != null) {
            int h1 = headA == null ? 0 : headA.val;
            int h2 = headB == null ? 0 : headB.val;
            int sum = h1 + h2 + carry;
            if (res == null) {
                res = temp = new ListNode(sum % 10);
            } else {
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
            }
            carry = sum / 10;

            if (headA != null) {
                headA = headA.next;
            }
            if (headB != null) {
                headB = headB.next;
            }

        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return res;
    }
}
