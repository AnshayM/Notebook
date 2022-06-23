package pers.anshay.notebook.learn.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个列表的最小索引总和
 * <p>
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案
 * <p>
 * 提示:
 * 两个列表的长度范围都在 [1, 1000]内。
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 下标从0开始，到列表的长度减1。
 * 两个列表都没有重复的元素。
 * <p>
 * 思路：用HashMap存第一个字符串数组，下标为value，值为key
 * 然后遍历第二个数组，判断当前值的key是否存在，存在则筛选后加到list，最后把list转格式后输出
 * 筛选机制：和前一个下标和比较，小则清空list
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution6 {
    public static void main(String[] args) {
        String[] l1 = new String[]{"K", "KFC"};
        String[] l2 = new String[]{"K", "KFC"};
        String[] a = findRestaurant(l1, l2);
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        // 最小坐标和
        int min = -1;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int temp = map.get(list2[i]) + i;
                min = min > -1 ? min : temp;
                if (temp > min) {
                    continue;
                }
                if (temp < min) {
                    min = temp;
                    list.clear();
                }
                list.add(list2[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
