package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 翻转二叉树
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

