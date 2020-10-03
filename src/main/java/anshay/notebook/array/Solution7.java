package anshay.notebook.array;


import anshay.notebook.util.MyUtil;

/**
 * 实现strStr()
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 *
 * @author: Anshay
 * @date: 2019/5/14
 */
public class Solution7 {
    public static void main(String[] args) {
        int a = strStr("heedfgdfgdfagllow", "gdfa");
        MyUtil.print(a);
    }

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if ("".equals(needle)) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
            /*找到第一个字符*/
            if (haystack.charAt(i) != needle.charAt(0)) {
                while (++i < haystack.length() && haystack.charAt(i) != needle.charAt(0)) ;
            }
            /*如果剩余从i开始的长度小于目标长度，则直接返回-1*/
            if (haystack.length() - i < needle.length()) {
                return -1;
            }
            int j = i + 1;
            int k = 1;
            /*从第一个开始找剩下的*/
            for (; k < needle.length() && haystack.charAt(j) == needle.charAt(k); k++, j++) ;
            if (k == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
