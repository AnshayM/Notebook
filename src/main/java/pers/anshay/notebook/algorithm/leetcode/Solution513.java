package pers.anshay.notebook.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 找树左下角的值
 * <p>
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 思路，用一个数组记录已经遍历到的最大深度节点的值
 *
 * @author: Anshay
 * @date: 2019/4/26
 */
public class Solution513 {
    public int findBottomLeftValue1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    /*方法2*/
    public int findBottomLeftValue(TreeNode root) {
        int[] res = dfs(root, 1);
        return res[0];
    }

    public int[] dfs(TreeNode node, int deep) {
        int[] res = {node.val, deep};
        if (node.left != null) {
            int[] leftRes = dfs(node.left, deep+1);
            res = leftRes;
        }
        if (node.right != null) {
            int[] rightRes = dfs(node.right,deep+1);
            if (res[1] < rightRes[1]) {
                res = rightRes;
            }
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
