package leecode;

/**
 * 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
 * +----+---------+
 * | Id | Email   |
 * +----+---------+
 * | 1  | a@b.com |
 * | 2  | c@d.com |
 * | 3  | a@b.com |
 * +----+---------+
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution182 {
    String sql = "SELECT email from person GROUP BY email having count(*)>1";
}
