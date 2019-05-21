package test;

import linkedlist.MyLinkedList;
import util.ListNode;
import util.MyUtil;

import java.util.LinkedList;

/**
 * @author: Anshay
 * @date: 2019/4/19
 */
public class test {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = node1;
        node1 = new ListNode(3);
    }

     static class ListNode {
        public int val;
        public util.ListNode next;

        public ListNode() {
        }

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
