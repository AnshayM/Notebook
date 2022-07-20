package Test4;

public class FindFirstIntersectionNodeInTwoLinkedListTest {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //最终调用的方法，用来获取head1和head2的交点
    public static Node getIntersectionNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loopNode1 = getEntryLoopNodeInLinkedList(head1);//获取head1链表的入环节点
        Node loopNode2 = getEntryLoopNodeInLinkedList(head2);//获取head2链表的入环节点
        if (loopNode1 == null && loopNode2 == null) {//两链表无环相交
            return intersectionInLinkedListWithoutLoop(head1, head2);
        }
        if (loopNode1 != null && loopNode2 != null) {//两链表有环相交
            return intersectionInLinkedLstWithLoop(head1, loopNode1, head2, loopNode2);
        }
        return null;
    }

    //获取有环链表的入环节点，如果没有，返回null
    public static Node getEntryLoopNodeInLinkedList(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slowPtr = head.next;//慢指针
        Node fastPtr = head.next.next;//快指针
        while (slowPtr != fastPtr) {
            if (fastPtr.next == null || fastPtr.next.next == null) {
                return null;
            }
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        fastPtr = head;//将快指针重新指向链表头部
        while (slowPtr != fastPtr) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;
        }
        return slowPtr;
    }

    //如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public static Node intersectionInLinkedListWithoutLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int length = 0;
        while (cur1.next != null) {
            length++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            length--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        //此时的length就是链表head1和链表head2的长度差值，有可能为负数
        cur1 = length > 0 ? head1 : head2;//根据长度差值length判断，哪个链表长，就把其头结点赋给cur1
        cur2 = cur1 == head1 ? head2 : head1;//根据长度差值length判断，哪个链表短，就把其头结点赋给cur2
        length = Math.abs(length);
        while (length != 0) {
            length--;
            cur1 = cur1 != null ? cur1.next : null;
        }
        while (cur1 != cur2) {
            cur1 = cur1 != null ? cur1.next : null;
            cur2 = cur2 != null ? cur2.next : null;
        }
        return cur1;
    }

    //如果两个链表都有环，返回第一个相交节点，如果不想交，返回null
    public static Node intersectionInLinkedLstWithLoop(Node head1, Node entryNodeInLoop1, Node head2, Node entryNodeInLoop2) {
        Node cur1;
        Node cur2;
        if (entryNodeInLoop1 == entryNodeInLoop2) {//情形三(B)
            cur1 = head1;
            cur2 = head2;
            int length = 0;
            while (cur1 != entryNodeInLoop1) {//把入环节点作为遍历终止点，问题就转化为无环链表相交问题
                length++;
                cur1 = cur1.next;
            }
            while (cur2 != entryNodeInLoop2) {
                length--;
                cur2 = cur2.next;
            }
            cur1 = length > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            length = Math.abs(length);
            while (length != 0) {
                length--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//情形三(C)
            cur1 = entryNodeInLoop1.next;
            while (cur1 != entryNodeInLoop1) {
                if (cur1 == entryNodeInLoop2) {
                    return entryNodeInLoop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    //有环链表打印
    public static void printLinkedList(Node head) {
        Node loopNode = getEntryLoopNodeInLinkedList(head);
        if (loopNode == null) {//无环链表
            while (head != null) {
                System.out.println(head.value + ", ");
                head = head.next;
            }
        }
        //对于有环链表，我们要遍历到最后一个节点，因为loopNode的前一个节点的next和链表最后一个节点的next均指向loopNode
        //当指针遍历的时候，我们要放过第一次路过loopNode的行为，但第二次路过loopNode的行为要禁止，此时的指针就是链表尾节点
        int meetingCount = 0;
        while (head != null && meetingCount != 2) {//有环链表
            System.out.print(head.value + ",");
            if (head.next == loopNode) {
                meetingCount++;
            }
            head = head.next;
        }
    }

    //测试
    public static void main(String[] args) {
        //构造两个链表（略）

    }
}