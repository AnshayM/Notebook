package pers.anshay.notebook.algorithm.sword2offer;

import pers.anshay.notebook.common.bo.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 标题
 * 和为指定值的路径
 * <p>
 * 题目描述
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。​
 * <p>
 * 示例1:​
 * ​
 * ​
 * ​
 * ​
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22​
 * 输出：[[5,4,11,2],[5,8,4,5]]​
 *
 * @author machao
 * @date 2022/5/16
 */
public class Solution32 {
	List<List<Integer>> ret = new LinkedList<List<Integer>>();
	Deque<Integer> path = new LinkedList<>();

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		dfs(root, sum);
		return ret;
	}


	public void dfs(TreeNode root, int target) {
		if (root == null) {
			return;
		}
		path.offerLast(root.val);
		target -= root.val;
		if (root.left == null && root.right == null && target == 0) {
			ret.add(new LinkedList<>(path));
		}
		dfs(root.left, target);
		dfs(root.right, target);
		path.pollLast();
	}
}
