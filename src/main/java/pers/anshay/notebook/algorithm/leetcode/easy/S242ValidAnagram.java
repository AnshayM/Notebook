package pers.anshay.notebook.algorithm.leetcode.easy;

import pers.anshay.notebook.common.util.MyUtil;

import java.util.Arrays;

/**
 * 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 思路：特定字母有固定的数目，解法：字母做键，出现次数做值。遍历第一个字符串去加、遍历第二个字符串去减，
 * 最后去判断这个 数组/map 是否所有键对应的值都为0
 * <p>
 * 使用HashMap的开销较大，使用数组可以考虑a-z一共26ge或者所有ascii码266个。
 * 我第一个想到的是排序后进行比对是否值相同，然后转化回String进行equals比较。
 * <p>
 * 法1性能略优于法2
 *
 * @author: Anshay
 * @date: 2019/7/4
 */
public class S242ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean res = isAnagram(s, t);
        MyUtil.print(res);
    }

    /*6ms-38m*/
    public static boolean isAnagram(String s, String t) {
        if (s == t) {
            return true;
        } else if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        String s1 = new String(ss);
        String t1 = new String(tt);
        return s1.equals(t1);
    }

    /*8ms-40.5m*/
    public static boolean isAnagram1(String s, String t) {
        if (s == t) {
            return true;
        } else if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] n = new int[26];
        for (char ch : s.toCharArray()) {
            n[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            n[ch - 'a']--;
        }
        for (int i : n) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
