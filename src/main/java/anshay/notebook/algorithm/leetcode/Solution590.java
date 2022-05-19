package anshay.notebook.algorithm.leetcode;

import anshay.notebook.common.pojo.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 590 N 叉树的后序遍历
 * 参见589
 *
 * @author machao
 * @date 2021/4/8
 */
public class Solution590 {
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    public void postorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            postorder(node, list);
        }
        list.add(root.val);
    }

}