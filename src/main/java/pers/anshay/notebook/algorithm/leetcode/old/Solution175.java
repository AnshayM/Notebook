package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 组合两个表
 * 编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
 * <p>
 * 思路：左连接
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution175 {
    String sql = "select p.FirstName, p.LastName, a.City, a.State from Person p left join Address a on p.PersonId = a.PersonId";
}
