package anshay.notebook.learn.hashtable;

import anshay.notebook.common.bo.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 寻找重复的子树
 * <p>
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * <p>
 * 思路：递归查询到子节点，然后每一步存储path，每一步用一个符号代替。将path和次数存到hashmap中，取次数大于1的存到list中作为结果返回
 *
 * @author: Anshay
 * @date: 2019/5/27
 */
public class Solution12 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap();
        findDuplicateSubtrees(root, res, map);
        return res;
    }

    public String findDuplicateSubtrees(TreeNode root, List<TreeNode> res, Map<String, Integer> map) {
        if (root == null) {
            return "#";
        }
        String left = findDuplicateSubtrees(root.left, res, map);
        String right = findDuplicateSubtrees(root.right, res, map);
        String path = root.val + "," + left + "," + right;
        map.put(path, map.getOrDefault(path.toString(), 0) + 1);
        if (map.get(path) == 2) {
            res.add(root);
        }
        return path;
    }
}
