package demo.listNode;

import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {
    private static Map<Node, Node> map = new HashMap<>();;

    public static void main(String[] args) {
        Integer[][] testData = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
        Node head = convert(testData);
        System.out.println(copyRandomList(head));
    }

    private static Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }
        while (!map.containsKey(head)) {
            Node newNode = new Node(head.val);
            map.put(head, newNode);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
        }
        return map.get(head);
    }

    public static Node convert(Integer[][] array) {
        if (array == null || array.length == 0) return null;

        Map<Integer, Node> nodeMap = new HashMap<>();
        Node dummy = new Node(0);
        Node current = dummy;

        // 第一遍创建所有节点并建立next关系
        for (Integer[] pair : array) {
            if (pair == null || pair.length < 1) continue;

            Node node = new Node(pair[0]);
            nodeMap.put(pair[0], node);
            current.next = node;
            current = current.next;
        }

        // 第二遍建立random关系
        current = dummy.next;
        for (Integer[] pair : array) {
            if (pair == null || pair.length < 2) {
                current = current.next;
                continue;
            }

            if (pair[1] != null) {
                current.random = nodeMap.get(pair[1]);
            }
            current = current.next;
        }

        return dummy.next;
    }
}
