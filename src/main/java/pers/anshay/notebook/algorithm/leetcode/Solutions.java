package pers.anshay.notebook.algorithm.leetcode;

import pers.anshay.notebook.common.bo.TreeNode;

import java.util.*;

/**
 * 题目太多，这里记录所有的方法调用
 *
 * @author machao
 * @date 2022/8/13
 */
public class Solutions {

    /**
     * 2283. 判断一个数的数字计数是否等于数位的值
     *
     * @param num
     * @return
     */
    public static boolean digitCount(String num) {
        int[] ct = new int[10];
        char[] chars = num.toCharArray();
        for (char ch : chars) {
            ct[ch - '0']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (ct[i] != chars[i] - '0') {
                return false;
            }
        }
        return true;
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<String>();
        for (String word : words) {
            if (match(word, pattern) && match(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public static boolean match(String word, String pattern) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < word.length(); ++i) {
            char x = word.charAt(i), y = pattern.charAt(i);
            if (!map.containsKey(x)) {
                map.put(x, y);
            } else if (map.get(x) != y) { // word 中的同一字母必须映射到 pattern 中的同一字母上
                return false;
            }
        }
        return true;
    }

    /**
     * LCP 44. 开幕式焰火
     *
     * @param root
     * @return
     */
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        numColor(root, set);
        return set.size();
    }

    public void numColor(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        numColor(root.left, set);
        numColor(root.right, set);
    }

    /**
     * 1684
     *
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            set.add(c);
        }
        int num = 0;
        for (String word : words) {
            int isIn = 1;
            for (char c : word.toCharArray()) {
                if (!set.contains(c)) {
                    isIn = 0;
                    break;
                }
            }
            num += isIn;
        }
        return num;
    }

    public int countConsistentStrings2(String allowed, String[] words) {
        int[] array = new int[26];
        for (char c : allowed.toCharArray()) {
            array[c - 'a']++;
        }
        int count = words.length;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (array[c - 'a'] == 0) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 1442. 形成两个异或相等数组的三元组数目
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        // todo 不会
        return 0;
    }
}
