package pers.anshay.notebook.algorithm.leetcode.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author machao
 * @date 2022/8/1
 */
public class Solution17 {

    public static void backtrack(List<String> combinations, String digits, Map<Character, String> phoneMap, int index, StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        // 对每个字符遍历
        String s = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < s.length(); i++) {
            // 进行遍历，同时处理好临时变量combination
            combination.append(s.charAt(i));
            backtrack(combinations, digits, phoneMap, index + 1, combination);
            combination.deleteCharAt(index);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(result, digits, phoneMap, 0, new StringBuilder());
        return result;

    }


}
