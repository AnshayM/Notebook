package leecode;

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
            for (int j = 0; j < strs.length; j++) {
                if (!strs[j].startsWith(temp)) {
                    return common;
                }
            }
            common = temp;
        }
        return common;
    }
}
