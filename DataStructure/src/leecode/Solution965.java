package leecode;

/**
 * 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution965 {
    Integer value;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (value == null) {
            value = root.val;
        }
        return value == root.val && isUnivalTree(root.left) && isUnivalTree(root.right);
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
