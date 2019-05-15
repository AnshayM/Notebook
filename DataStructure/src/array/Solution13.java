package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * @author: Anshay
 * @date: 2019/5/15
 */
public class Solution13 {
    public static void main(String[] args) {
        List<Integer> list = getRow(5);
    }

    /*是有公式的*/
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) cur);
            cur = cur * (rowIndex-i)/(i+1);
        }
        return res;
    }

    public static List<Integer> getRow1(int rowIndex) {
        rowIndex++;
        if (rowIndex == 0) {
            return new ArrayList<>();
        } else if (rowIndex == 1) {
            return Arrays.asList(new Integer[]{1});
        } else if (rowIndex == 2) {
            return Arrays.asList(new Integer[]{1, 1});
        }
        List<Integer> preList = Arrays.asList(new Integer[]{1, 1});
        List<Integer> resList = new ArrayList<>();
        for (int i = 2; i < rowIndex; i++) {
            resList = new ArrayList<>();
            resList.add(1);
            for (int j = 0; j < preList.size() - 1; j++) {
                resList.add(preList.get(j) + preList.get(j + 1));
            }
            resList.add(1);
            if (i + 1 != rowIndex) {
                preList = resList;
            }
        }
        return resList;
    }

}
