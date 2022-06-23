package pers.anshay.notebook.learn.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution5 {
    public static void main(String[] args) {
        String s = "add";
        String t = "taa";
        boolean flag = isIsomorphic1(s, t);
    }

    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[255];
        int[] map2 = new int[255];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
            /*加 1 是为了和默认值 0 区分*/
            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static boolean isIsomorphic1(String s, String t) {
        /*相等时，包括了都为null*/
        if (s == t) {
            return true;
        }
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch1 = map1.get(s.charAt(i));
            Character ch2 = map2.get(t.charAt(i));
            if (ch1 == null) {
                map1.put(s.charAt(i), t.charAt(i));
            } else if (ch1 != t.charAt(i)) {
                return false;
            }
            if (ch2 == null) {
                map2.put(t.charAt(i), s.charAt(i));
            } else if (ch2 != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
