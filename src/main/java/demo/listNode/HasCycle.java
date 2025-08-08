package demo.listNode;

import java.util.HashSet;
import java.util.Set;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class HasCycle {
    public static void main(String[] args) {
        int[] head = {5, 5, 5, 5};
        ListNode headA = arrayToList(head);
        System.out.println(hasCycle(headA));
    }

    private static boolean hasCycle(ListNode headA) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (headA != null) {
            if (set.contains(headA)) {
                return true;
            }
            set.add(headA);
            headA = headA.next;
        }
        return false;
    }

}
