package demo.listNode;

import java.util.ArrayList;
import java.util.List;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class IsPalindrome {
    public static void main(String[] args) {
        int[] head = {1, 2, 2, 1};
        ListNode headA = arrayToList(head);
        System.out.println(isPalindrome(headA));
    }

    private static boolean isPalindrome(ListNode headA) {
        List<Integer> list = new ArrayList<Integer>();
        while (headA != null) {
            list.add(headA.val);
            headA = headA.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
