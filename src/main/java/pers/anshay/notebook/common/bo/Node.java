package pers.anshay.notebook.common.bo;

import java.util.List;

/**
 * @author: Anshay
 * @date: 2019/5/20
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
