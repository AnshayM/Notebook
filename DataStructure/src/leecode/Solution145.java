package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历(相关题目：589：N叉树的前序遍历)
 * 前中后遍历和根节点位置有关，
 * 前：根-左-右
 * 中：左-根-右
 * 后：左-右-根
 *
 * @author: Anshay
 * @date: 2019/4/26
 */
public class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    public void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
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
