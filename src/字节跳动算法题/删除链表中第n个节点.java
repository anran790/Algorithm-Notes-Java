package 字节跳动算法题;

/**
 * 删除链表中倒数第 N 个节点
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
class Node {
    int val;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

public class 删除链表中第n个节点 {
    public static void main(String[] args) {
        // [1,2,3,4,5]
        Node n5 = new Node(5);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        Node head = n1;
        printList(removeNodeIdx(head, 2));
    }

    public static Node removeNodeIdx(Node head, int n) {
        // 虚拟头，统一处理删除头结点边界
        Node dummy = new Node(-1, head);
        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 打印函数
     *
     * @param head
     */
    public static void printList(Node head) {
        Node cur = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append("]");
        System.out.println(sb);
    }

}
