package leecode.easy;

import java.util.List;

/**
 * N叉树的最大深度
 * 参考104，2二叉树的最大深度
 *
 * @author: Anshay
 * @date: 2019/10/24
 */
public class Solution559 {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (Node node : root.children) {
            int depth2 = maxDepth(node);
            depth = depth >= depth2 ? depth : depth2;
        }
        return depth + 1;
    }

    /**
     * 用到的node
     */
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}


