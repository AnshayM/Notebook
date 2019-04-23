package leecode;

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
public class Solution104 {

    public int maxDepth(TreeNode root) {
        int num = 1;
        num = maxDepth(root, num);
        return num;
    }

    public int maxDepth(TreeNode root, int num) {
        if (root == null) {
            return num - 1;
        }
        int leftDeep = 0;
        int rightDeep = 0;
        if (root != null) {
            leftDeep = maxDepth(root.left, num);
            rightDeep = maxDepth(root.right, num);
        }
        return leftDeep >= rightDeep ? leftDeep : rightDeep;
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