package pers.anshay.notebook.learn.hashtable;

import java.util.*;

/**
 * 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串
 * <p>
 * 将每一个字符串排序后存hashmap，排序后的字符串为键，存储满足条件String的List<String>为值，返回hashmap的values集合
 *
 * @author: Anshay
 * @date: 2019/5/24
 */
public class Solution10 {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }
}
