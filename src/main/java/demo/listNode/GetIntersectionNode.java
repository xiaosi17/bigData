package demo.listNode;

import java.util.HashSet;
import java.util.Set;

public class GetIntersectionNode {
    public static void main(String[] args) {
        int[] listA = {4, 1, 8, 4, 5};
        ListNode headA = arrayToList(listA);
        int[] listB = {5, 6, 1, 8, 4, 5};
        ListNode headB = arrayToList(listB);
        ListNode res = getIntersectionNode(headA, headB);
        System.out.println(res.val);
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (headA != null) {
            visited.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (visited.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public static ListNode arrayToList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }
}


