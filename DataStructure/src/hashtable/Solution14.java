package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 2^31 - 1
 * <p>
 * 思路：只考虑满足条件的，将四数分开为两数。（A+B）和（C+D）,计算出所有A+B的结果存在hashmap里，然后在这个map里找键值为 -(C+D)的项，value即个数。
 * 前面遍历所有A+B时有重复会将value+1，后面遍历所有C+D时，直接重复加在count上了
 *
 * @author: Anshay
 * @date: 2019/5/27
 */
public class Solution14 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int numA : A) {
            for (int numB : B) {
                map.put(numA + numB, map.getOrDefault(numA + numB, 0) + 1);
            }
        }
        for (int numC : C) {
            for (int numD : D) {
                count += map.getOrDefault(-numC - numD, 0);
            }
        }
        return count;
    }
}
