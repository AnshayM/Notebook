package pers.anshay.notebook.algorithm.leetcode;

import pers.anshay.notebook.common.bo.TreeNode;

import java.util.*;

/**
 * 题目太多，这里记录所有的方法调用
 *
 * @author machao
 * @date 2022/8/13
 */
public class Solutions {

    public List<TreeNode> allPossibleFBT(int n) {
        // todo 未完成
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) {
            return res;
        }
        return null;
    }

    /**
     * 剑指 Offer II 091. 粉刷房子
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int[] dp = new int[3];
        System.arraycopy(costs[0], 0, dp, 0, 3);
        for (int i = 1; i < costs.length; i++) {
            int[] temp = new int[3];
            for (int j = 0; j < 3; j++) {
                temp[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            dp = temp;
        }
        return Arrays.stream(dp).min().getAsInt();

    }


    /**
     * 试题 08.09. 括号
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        doGenerateParenthesis(res, stringBuilder, n, n);
        return res;
    }

    private void doGenerateParenthesis(List<String> res, StringBuilder stringBuilder, int left, int right) {
        if (right == 0) {
            res.add(stringBuilder.toString());
            return;
        }
        if (right > left) {
            stringBuilder.append(')');
            doGenerateParenthesis(res, stringBuilder, left, right - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (left > 0) {
            stringBuilder.append('(');
            doGenerateParenthesis(res, stringBuilder, left - 1, right);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }


    /**
     * 746. 使用最小花费爬楼梯
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int pre = 0, cur = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(cur + cost[i], pre + cost[i - 1]);
            pre = cur;
            cur = next;
        }
        return cur;
    }

    /**
     * 70 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int f0 = 1, f1 = 2;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            int temp = f1;
            f1 += f0;
            f0 = temp;
        }
        return f1;

    }

    /**
     * 2283. 判断一个数的数字计数是否等于数位的值
     *
     * @param num
     * @return
     */
    public static boolean digitCount(String num) {
        int[] ct = new int[10];
        char[] chars = num.toCharArray();
        for (char ch : chars) {
            ct[ch - '0']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (ct[i] != chars[i] - '0') {
                return false;
            }
        }
        return true;
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<String>();
        for (String word : words) {
            if (match(word, pattern) && match(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public static boolean match(String word, String pattern) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < word.length(); ++i) {
            char x = word.charAt(i), y = pattern.charAt(i);
            if (!map.containsKey(x)) {
                map.put(x, y);
            } else if (map.get(x) != y) { // word 中的同一字母必须映射到 pattern 中的同一字母上
                return false;
            }
        }
        return true;
    }

    /**
     * LCP 44. 开幕式焰火
     *
     * @param root
     * @return
     */
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        numColor(root, set);
        return set.size();
    }

    public void numColor(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        numColor(root.left, set);
        numColor(root.right, set);
    }

    /**
     * 1684
     *
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            set.add(c);
        }
        int num = 0;
        for (String word : words) {
            int isIn = 1;
            for (char c : word.toCharArray()) {
                if (!set.contains(c)) {
                    isIn = 0;
                    break;
                }
            }
            num += isIn;
        }
        return num;
    }

    public int countConsistentStrings2(String allowed, String[] words) {
        int[] array = new int[26];
        for (char c : allowed.toCharArray()) {
            array[c - 'a']++;
        }
        int count = words.length;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (array[c - 'a'] == 0) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

    public int countAsterisks(String s) {
        int ct = 0;
        boolean flag = false;
        s.charAt(0);
        for (char c : s.toCharArray()) {
            if ('|' == c) {
                flag = !flag;
            } else if ('*' == c && !flag) {
                ct++;
            }
        }
        return ct;
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<>();
        int max = -1;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        for (int candy : candies) {
            list.add(candy + extraCandies >= max);
        }
        return list;
    }

    /**
     * 1442. 形成两个异或相等数组的三元组数目
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        // todo 不会
        return 0;
    }
}
