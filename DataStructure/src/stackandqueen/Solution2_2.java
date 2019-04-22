package stackandqueen;

import java.util.*;

/**
 * 打开轮盘锁（使用Set，双向）
 *
 * @author: Anshay
 * @date: 2019/4/1
 */
public class Solution2_2 {

    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        String init = "0000";
        if (dead.contains(init) || dead.contains(target)) {
            return -1;
        }
        if (target.equals(init)) {
            return 0;
        }

        //存放从初始值衍生的节点
        Set<String> set1 = new HashSet<>();
        set1.add(init);
        // 存放目标值衍生的节点
        Set<String> set2 = new HashSet<>();
        set2.add(target);
        int step = 0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            // 始终选择数据量小的进行遍历搜索，减少开销
            if (set1.size() > set2.size()) {
                Set<String> temp = set2;
                set2 = set1;
                set1 = temp;
            }
            Set<String> set3 = new HashSet<>();
            for (String cur : set1) {
                for (String neighbor : getNeighbor(cur)) {
                    if (set2.contains(neighbor)) {
                        return step + 1;
                    }
                    if (!dead.contains(neighbor) && visited.contains(neighbor)) {
                        visited.add(neighbor);
                        set3.add(neighbor);
                    }
                }
            }
            step++;
            set1 = set3;
        }
        return -1;
    }

    public List<String> getNeighbor(String cur) {
        List<String> neighbors = new LinkedList<>();
        for (int i = 0; i < cur.length(); i++) {
            // 每位数两个邻节点
            char ch = cur.charAt(i);
            char newChar = ch == '0' ? '9' : (char) (ch - 1);
            StringBuilder sb = new StringBuilder(cur);
            sb.setCharAt(i, newChar);
            neighbors.add(sb.toString());

            newChar = ch == '9' ? '0' : (char) (ch + 1);
            sb.setCharAt(i, newChar);
            neighbors.add(sb.toString());
        }
        return neighbors;
    }
}
