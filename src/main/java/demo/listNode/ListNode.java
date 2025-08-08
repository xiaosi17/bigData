package demo.listNode;

import java.util.Objects;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode nex) {
        val = x;
        next = nex;
    }


    @Override
    public boolean equals(Object obj) {
        // 1. 自反性检查
        if (this == obj) return true;

        // 2. 非空检查和类型检查
        if (obj == null || getClass() != obj.getClass())
            return false;

        // 3. 类型转换
        ListNode other = (ListNode) obj;

        // 4. 值比较和递归检查next节点
        return this.val == other.val
                && (this.next == other.next || (this.next != null && this.next.equals(other.next)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

}
