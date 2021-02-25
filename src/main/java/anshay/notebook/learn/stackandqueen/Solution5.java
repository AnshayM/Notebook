package anshay.notebook.learn.stackandqueen;

import java.util.Stack;

/**
 * 每日温度：
 * 用队列存数组的下标值n，然后遍历比较找到第一个大于T[stack.peek()]的元素，获取其下标，下标之差就是相差的天数
 * 有可能存在3、2、4的情况，即第三天里可以得到前两天的下标的值。
 *
 * @author: Anshay
 * @date: 2019/4/3
 */
public class Solution5 {
    public int[] dailyTemperatures(int[] T) {
        int[] days = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                // 对应的哪一天
                int curr = stack.pop();
                days[curr] = i - curr;
            }
            stack.push(i);
        }
        return days;
    }
}
