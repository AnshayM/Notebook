package anshay.notebook.algorithm.leetcode;

import anshay.notebook.common.pojo.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 589 N 叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * 中-左-右
 * <p>
 * 带参数进去，比每次新建一个参数要好
 *
 * @author: Anshay
 * @date: 2019/4/25
 */
public class Solution589 {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node node : root.children) {
            preorder(node, list);
        }
    }

}
