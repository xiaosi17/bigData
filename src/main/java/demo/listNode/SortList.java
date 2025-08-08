package demo.listNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class SortList {
    public static void main(String[] args) {
        int[] head = {4, -2, 3, 1};
        ListNode headA = arrayToList(head);
        System.out.println(sortList(headA));
    }

    private static ListNode sortList(ListNode headA) {
        List<Integer> list = new ArrayList<Integer>();
        while (headA != null) {
            list.add(headA.val);
            headA = headA.next;
        }
        Collections.sort(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return arrayToList(res);
    }
}
