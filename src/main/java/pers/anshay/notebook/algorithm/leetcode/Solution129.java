package pers.anshay.notebook.algorithm.leetcode;

import pers.anshay.notebook.common.bo.TreeNode;

/**
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 *
 * @author machao
 * @date 2020/10/30
 */
public class Solution129 {
    public int sumNumbers(TreeNode node) {
        return dfs(node, 0);
    }

    public int dfs(TreeNode node, int prevSum) {
        if (node == null) {
            return 0;
        }
        int sum = prevSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return sum;
        } else {
            return dfs(node.left, sum) + dfs(node.right, sum);
        }
    }
}
