package anshay.notebook.leetcode;

/**
 * 上升的温度
 * 给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
 * <p>
 * DATEDIFF是两个日期的天数差集，用一个子查询限定查询出前一天的温度
 * on后面用and比where要快
 *
 * @author: Anshay
 * @date: 2019/5/8
 */
public class Solution197 {
    String sql = "select w1.id from weather w1 join weather w2 on datediff(w1.recorddate,w2.recorddate)=1 " +
            "and w1.temperature>w2.temperature ";
}

