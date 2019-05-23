package hashtable;

import java.util.*;

/**
 * 字符串中的第一个唯一字符
 * <p>
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution7 {
    public static void main(String[] args) {
        String s = "asdfasdfgasdfasdfhasdfj";
        int a = firstUniqChar(s);
    }

    /*用数组自己方法*/
    public static int firstUniqChar(String s) {
        if (s == null || "".equals(s)) {
            return -1;
        }
        int min = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if (index != -1 && index == s.lastIndexOf(ch)) {
                min = min == -1 ? index : (min < index ? min : index);
            }
        }
        return min;

    }

    public static int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), null);
            } else {
                map.put(s.charAt(i), i);
            }
        }
        Integer[] indexs = map.values().toArray(new Integer[]{});
        if (indexs == null || indexs.length == 0) {
            return -1;
        }
        int index = -1;
        for (Integer it : indexs) {
            if (it != null) {
                index = index != -1 ? index : it;
                index = index < it ? index : it;
            }
        }
        return index;
    }

}
