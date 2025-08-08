package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class RotateRight {
    public static void main(String[] args) {
        int[] head = {0, 1, 2};
        ListNode headA = arrayToList(head);
        int k = 4;
        System.out.println(rotateRight(headA, k));
    }

    private static ListNode rotateRight(ListNode headA, int k) {
        //
        int len = 0;
        ListNode temp = headA;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        int j = 0;
        if (len <= k) {
            j = k % len;
        } else {
            j = k;
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            if (i < len - j) {
                res[i + j] = headA.val;
            } else {
                res[i - j - 1] = headA.val;
            }
            headA = headA.next;
        }

        return arrayToList(res);
    }
}
