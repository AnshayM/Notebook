package anshay.notebook.leetcode;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author machao
 * @date 2020/10/19
 */
public class Solution844 {
    /**
     * 方法1，使用栈
     * 思路：1用栈，遇到"#"时退两个
     */
    public boolean backspaceCompare1(String S, String T) {
        return getResult(S, T);
        // return getString(S).equals(getString(T));
    }

    String getString(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c.equals('#')) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    /**
     * 方法2-双指针
     * 思路：从后向前遍历，比较每个字符，过滤掉被#删除的字符
     *
     * @param S
     * @param T
     * @return
     */
    boolean getResult(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            //从后向前找第一个有效字符（不被跳过的字符）
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            //从后向前找第一个有效字符（不被跳过的字符）
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    //出现不一样的字符，返回false
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    //出现长度不一样的情况，即其中一个到了首端，另一个没有
                    return false;
                }
            }

            //一轮结束，索引向前移动一位
            i--;
            j--;
        }
        //结束循环，未跳出，则返回true
        return true;
    }

    /**
     * 思路：遍历每个项，如果是负数，就直接比较当前值，正数才进行计算
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //记录最大的值
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (num >= 0) {
                sum += num;
            } else {
                //当出现num<0时，上一个已经是最大了且被记录了
                sum = num;
            }
            res = Math.max(res, sum);
        }


        return 0;
    }


}
