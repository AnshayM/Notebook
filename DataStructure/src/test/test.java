package test;

import linkedlist.MyLinkedList;
import util.ListNode;
import util.MyUtil;

import java.util.*;

/**
 * @author: Anshay
 * @date: 2019/4/19
 */
public class test {
    public static void main(String[] args) {
        int[] n = new int[0];

    }
    public int numJewelsInStones(String J, String S) {
        if (J == null || "".equals(J) || S == null | "".equals(S)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (J.indexOf(ch) > -1) {
                count++;
            }
        }
        return count;
    }
}
