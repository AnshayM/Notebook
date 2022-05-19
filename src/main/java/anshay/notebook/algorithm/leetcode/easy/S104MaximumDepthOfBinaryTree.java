package anshay.notebook.algorithm.leetcode.easy;

import anshay.notebook.common.pojo.TreeNode;

/**
 * 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class S104MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLen = 1 + maxDepth(root.left);
        int rightLen = 1 + maxDepth(root.right);
        return Math.max(leftLen, rightLen);
    }

}
