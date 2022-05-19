package anshay.notebook.algorithm.leetcode;

/**
 * 最长公共前缀
 * <p>
 * 题库最快的方法是找到最短的字符串，然后从长到短的查询。
 * 我觉得这个要结合具体的数据来看。题库给的数据字符串一般较短，所以从尾部开始可能更快，但是数据量大的时候，这种方法不适合。
 *
 * @author: Anshay
 * @date: 2019/4/18
 */
public class Solution14 {

    public static void main(String[] args) {
        String[] ss = new String[]{"flower", "flow", "flight"};
        String s = longestCommonPrefix(ss);
        System.out.println(s);
    }

    /**纵向比较时，只需要比较对应i位置的字符即可，不用比较startsWith，startsWith把前面的都比较了一遍
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        String common = "";
        String temp;
        if (strs == null || strs.length == 0) {
            return common;
        } else if (strs.length == 1) {
            return strs[0];
        }
        for (int i = 0; i < strs[0].length(); i++) {
            temp = strs[0].substring(0, i + 1);
            for (String str : strs) {
                if (!str.startsWith(temp)) {
                    return common;
                }
            }
            common = temp;
        }
        return common;
    }

    public static String longestCommonPrefix2(String[] strs) {
        StringBuilder res = new StringBuilder();
        if (strs == null || strs.length == 0) {
            return res.toString();
        } else if (strs.length == 1) {
            return strs[0];
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // 超过长度或者指定位置不相同，则直接返回结果
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return res.toString();
                }
                // 遍历到最后一个，则把这个字母加进去
                if (j == strs.length - 1) {
                    res.append(ch);
                }
            }
        }
        return res.toString();
    }
}
