package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *克隆图
 * @author: Anshay
 * @date: 2019/4/15
 */
public class Solution133 {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node clone = new Node(node.val, new ArrayList<Node>());
        for (Node neighbooar : node.neighbors) {
            if (!map.containsKey(neighbooar)) {
                Node newNeighbor = cloneGraph(neighbooar);
                clone.neighbors.add(newNeighbor);
            } else {
                clone.neighbors.add(map.get(neighbooar));
            }
        }
        return clone;
    }
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
};
