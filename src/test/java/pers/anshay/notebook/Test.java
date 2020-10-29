package pers.anshay.notebook;

import anshay.notebook.common.pojo.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {


    //思路，用一个长度
    public boolean uniqueOccurrences(int[] arr) {
        //0-2000
        int[] cnt = new int[2001];
        for (int i = 0; i < arr.length; i++) {
            ++cnt[arr[i] + 1000];
        }
        Set<Integer> set = new HashSet<>();
        for (int i : cnt) {
            if (i == 0) {
                continue;
            }
            if (!set.add(i)) {
                return false;
            }
        }
        return true;
    }





}
