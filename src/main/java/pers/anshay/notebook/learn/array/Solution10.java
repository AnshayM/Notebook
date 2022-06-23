package pers.anshay.notebook.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 *
 * @author: Anshay
 * @date: 2019/5/14
 */
public class Solution10 {
    public static void main(String[] args) {
        generate(5);
        int a = 0;
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> subList;
        List<Integer> preList;
        /*1层*/
        resList.add(Arrays.asList(new Integer[]{1}));
        if (numRows == 1) {
            return resList;
        }
        /*2层*/
        subList = new ArrayList<>();
        subList.add(1);
        subList.add(1);
        resList.add(Arrays.asList(new Integer[]{1, 1}));
        if (numRows == 2) {
            return resList;
        }
        for (int i = 2; i < numRows; i++) {
            subList = new ArrayList<>();
            preList = resList.get(i - 1);
            subList.add(1);
            for (int j = 0; j < preList.size() - 1; j++) {
                subList.add(preList.get(j) + preList.get(j + 1));
            }
            subList.add(1);
            resList.add(subList);
        }
        return resList;
    }
}
