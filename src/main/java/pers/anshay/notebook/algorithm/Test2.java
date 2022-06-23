package pers.anshay.notebook.algorithm;

import pers.anshay.notebook.common.bo.ListNode;

import java.util.Stack;

/**
 * @author machao
 * @date 2022/6/1
 */
public class Test2 {
	// public static void main(String[] args) {
	// 	ListNode l1 = new ListNode(0, new ListNode(1));
	// 	l1.next.next = new ListNode(2, new ListNode(3));
	// 	ListNode listNode = deleteNode(l1, 2);
	// 	while (listNode != null) {
	// 		System.out.println(listNode.val);
	// 		listNode = listNode.next;
	// 	}
	// }

	/**
	 * 删除倒数第N个
	 *
	 * @param listNode 目标结点头结点
	 * @param nth      第多少个，从1开始
	 * @return
	 */
	// 删除列表的倒数第N个结点
	public static ListNode deleteNode(ListNode listNode, int nth) throws Exception {
		ListNode pre = new ListNode();

		// 填充到栈
		pre.next = listNode;
		Stack<ListNode> stack = new Stack<>();
		while (listNode != null) {
			stack.push(listNode);
			listNode = listNode.next;
		}

		//检查
		if (stack.size() < nth) {
			throw new Exception("...");
		}

		// 进行回头遍历
		int i = 0;
		ListNode cur = null;
		assert nth > 0;
		while (i <= nth) {
			cur = stack.pop();
			i++;
		}
		cur.next = cur.next.next;

		return pre.next;
	}

	public static void main(String[] args) {
		int[] array = new int[]{1, 1, 2, 2, 3, 3, 4};
		int theSpecifiedValue = getTheSpecifiedValue(array);
		System.out.println(theSpecifiedValue);

	}

	/**
	 * 一个int数组里，只有一个元素只出现一次，其他都出现两次，找到这个元素
	 *
	 * @param array 数组
	 * @return 指定的数
	 */
	//int 数数组
	public static int getTheSpecifiedValue(int[] array) {
		if (array == null || array.length == 0) {
			// 默认-1为输入异常/或者抛出异常提示
			return -1;
		}
		if (array.length == 1) {
			return array[0];
		}

		int target = 0;
		for (int j : array) {
			// 用位运算处理，相同的会消失
			target ^= j;
		}

		return target;
	}

}
