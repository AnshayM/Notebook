package pers.anshay.notebook.algorithm.leetcode.old;

import java.util.*;

/**
 * 克隆图
 *
 * @author: Anshay
 * @date: 2019/4/15
 */
public class Solution133 {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node Node) {
        if (Node == null) {
            return null;
        }
        Node clone = new Node(Node.val, new ArrayList<Node>());
        map.put(Node, clone);
        //邻居节点
        for (Node neighbor : Node.neighbors) {
            //如果邻居节点在map中没有
            if (!map.containsKey(neighbor)) {
                Node newNeighbor = cloneGraph(neighbor);
                clone.neighbors.add(newNeighbor);
            } else {
                clone.neighbors.add(map.get(neighbor));
            }
        }
        return clone;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}


