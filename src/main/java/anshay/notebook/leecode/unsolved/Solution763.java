package anshay.notebook.leecode.unsolved;

import java.util.ArrayList;
import java.util.List;

/**
 * 763 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * @author machao
 * @date 2020/10/22
 */
public class Solution763 {
    public List<Integer> partitionLabels(String S) {
        //记录每个字母的最后下标
        int[] last = new int[26];
        int length = S.length();
        //这一遍将最后出现的下标记录在了last数组里
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        //记录长度列表的结果
        List<Integer> list = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            //更新当前片段的end值
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1;
            }
        }
        return list;
    }
}
