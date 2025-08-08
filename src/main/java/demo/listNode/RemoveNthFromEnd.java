package demo.listNode;

import static demo.listNode.GetIntersectionNode.arrayToList;

public class RemoveNthFromEnd {
    public static void main(String[] args) {
        int[] l1 = {1, 2, 3, 4, 5};
        ListNode head = arrayToList(l1);
        System.out.println(removeNthFromEnd(head, 2));
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode temp = new ListNode(0,head);
        int length = getLength(head);
        ListNode cur = temp;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return temp.next;
    }

    private static int getLength(ListNode head) {
        int len=0;
        while (head!=null){
            len++;
            head=head.next;
        }
        return len;
    }
}
