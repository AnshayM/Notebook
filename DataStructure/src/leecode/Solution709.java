package com.datastructure.leecode;

/**
 * 转换成小写字母
 * 考察String.toLowerCase()实现，可以用源码实现方式，也可以使用ascii码转换字符
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution709 {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 32);
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public String toLowerCase1(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 32);
            }
            sb.append(ch);
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }
}
