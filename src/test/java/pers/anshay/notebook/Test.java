package pers.anshay.notebook;

import anshay.notebook.util.ListNode;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int removeDuplicates2(int[] nums) {
        int course = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[course + 1] != nums[i] && course + 1 < nums.length) {
                //这里不一样，不相等时将下一位的值设为指定值
                nums[++course] = nums[i];
            }
        }
        //这里是下角标
        return course + 1;
    }

    /**
     * 重排链表
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode beforeHead = new ListNode(-1, head);
        //思路，分别用快慢指针，遍历一遍找到中间的。用栈录入慢节点
        //以中间为标准点，退栈和往后遍历，做插入操作
        ListNode first = beforeHead;
        ListNode slow = beforeHead;

    }
}
