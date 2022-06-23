package pers.anshay.notebook.algorithm.leetcode.core;

import pers.anshay.notebook.common.bo.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * @author machao
 * @date 2022/5/16
 */
public class Solution34 {
	List<List<Integer>> res = new LinkedList<>();
	Deque<Integer> route = new LinkedList<>();
	// 用res全局保存结果，route用来保存每一个具体的路径，注意offerLast 和poolLast
	public List<List<Integer>> pathSum(TreeNode root, int target) {
		dfs(root,target);
		return res;

	}

	public void dfs(TreeNode root,int target){
		// 到底或者已经超过大小
		if(root==null){
			return;
		}
		// 加入该值
		route.offerLast(root.val);
		target-=root.val;
		if(root.left==null&&root.right==null&&target==0){
			//满足条件，加入该值
			res.add(new LinkedList<>(route));
		}
		dfs(root.left,target);
		dfs(root.right,target);
		//每层递归，需要推出前面加的值
		route.pollLast();
	}

}
