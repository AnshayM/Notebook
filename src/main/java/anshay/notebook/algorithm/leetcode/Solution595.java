package anshay.notebook.algorithm.leetcode;

/**
 * 大的国家
 * <p>
 * 使用union在某些情况要比使用or、||快速一些
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution595 {
    String sql = "select name,population,area from world where population >= 25000000 \n" +
            "union select name,population,area from world where area >= 3000000";

    String sql1 = "select name,population,area from world where population >= 25000000 or area >= 3000000";
}
