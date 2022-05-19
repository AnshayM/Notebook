package anshay.notebook.algorithm.leetcode;

import anshay.notebook.common.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144 二叉树的前序序遍历(相关题目：589：N叉树的前序遍历)
 * 前中后遍历和根节点位置有关，
 * 前：根-左-右
 * 中：左-根-右
 * 后：左-右-根
 * 前序遍历
 *
 * @author machao
 * @date 2020/10/27
 */
public class Solution144 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    public void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
    }
}
