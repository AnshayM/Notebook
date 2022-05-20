package anshay.notebook.algorithm.leetcode.easy;

import anshay.notebook.common.bo.Node;

/**
 * N 叉树的最大深度
 * 参考104，2二叉树的最大深度
 *
 * @author: Anshay
 * @date: 2019/10/24
 */
public class S559MaximumDepthOfN_aryTree {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (Node node : root.children) {
            int depth2 = maxDepth(node);
            depth = Math.max(depth, depth2);
        }
        return depth + 1;
    }


}


