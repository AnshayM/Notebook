package anshay.notebook.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * 这里的方法做了数据判断的情况，因为数据是有效的，所以直接判断前一个比后一个小即可。
 * 使用switc代替hashmap也可以节省内存
 * 直接遍历charAt，相对转化为数组也可以节省性能
 *
 * @author: Anshay
 * @date: 2019/4/18
 */
public class Solution13 {
    public static void main(String[] args) {
        int a = romanToInt("III");
        System.out.println(a);
    }

    /**
     * 这从后往前遍历，每次存一个temp为上一位的数值
     */
    public static int romanToInt(String s) {
        int sum = 0, temp = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        for (int i = s.length() - 1; i >= 0; i--) {
            int count = map.get(s.charAt(i));
            sum += (count >= temp ? 1 : -1) * count;
            temp = count;
        }
        return sum;
    }

    public static int romanToInt1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] num1 = s.toCharArray();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int item = map.get(num1[i]);
            int isPositive = 1;
            if (i + 1 < s.length() && (num1[i] == 'I' || num1[i] == 'X' || num1[i] == 'C')) {
                isPositive = (map.get(num1[i]) < map.get(num1[i + 1]) && map.get(num1[i]) * 10 >= map.get(num1[i + 1])) ? -1 : isPositive;
            }
            result = result + isPositive * item;
        }
        return result;
    }
}
