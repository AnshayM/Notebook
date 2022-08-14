package pers.anshay.notebook.algorithm;

import pers.anshay.notebook.common.bo.ListNode;
import pers.anshay.notebook.common.bo.TreeNode;

import java.util.*;

/**
 * @author machao
 * @date 2022/5/19
 */
public class Test {
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for (String str : operations) {
            res += (44 - (int) (str.charAt(1)));
        }
        return res;
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    public int[] runningSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum;
        }
        return nums;
    }

    public int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int[] point : points) {
                res[i] += (point[0] - queries[i][0]) * (point[0] - queries[i][0])
                        + (point[1] - queries[i][1]) * (point[1] - queries[i][1])
                        > (queries[i][2]) * (queries[i][2]) ? 1 : 0;
            }

        }
        return res;
    }

    public int divide(int dividend, int divisor) {
        int a = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0 ? 1 : -1;
        return a * (Math.abs(dividend / divisor));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return false;
        } else if (p == null) {
            return true;
        }
        return p.val != q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public int minPartitions(String n) {
        int max = 0;
        for (char c : n.toCharArray()) {
            max = Math.max(max, c - '0');
        }
        return max;
    }

    public int mostWordsFound(String[] sentences) {
        int max = 0;
        for (String sentence : sentences) {
            max = Math.max(max, sentence.split(" ").length);
        }
        return max;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public int xorOperation(int n, int start) {
        int temp = start;
        for (int i = 1; i < n; i++) {
            temp = start ^ (start + 2 * i);
        }
        return temp;
    }

    public int minimumSum(int num) {
        int[] array = new int[4];
        for (int i = 0; i < array.length && num > 0; i++) {
            array[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(array);
        return (array[0] + array[1]) * 10 + array[2] + array[3];
    }

    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            count += map.getOrDefault(num + k, 0);
            count += map.getOrDefault(num - k, 0);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int lMax = 0, rMax = 0;
        int count = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] > lMax) {
                    // 更新边界
                    lMax = height[l];
                } else {
                    // 增加
                    count += lMax - height[l];
                }
                l++;
            } else {
                if (height[r] > rMax) {
                    rMax = height[r];
                } else {
                    count += rMax - height[r];
                }
                r--;
            }
        }
        return count;
    }

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get(nums[i]);
            if (integer != i) {
                return new int[]{integer, i};
            }

        }
        return nums;
    }

    public int[] twoSumII(int[] numbers, int target) {
        // 这个是说明了没有重复项目
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] < target) {
                l++;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                return new int[]{l + 1, r + 1};
            }
        }
        return new int[]{-1, -1};

    }

    public int sumNums(int n) {
        boolean b = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序，然后对一个for后变2数之和,等于0，则 -A= B+C
        // 在针对A的循环中，如果相等，继续往后找值，可能会有重复值
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            } else if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去重复
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    l--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    /**
     * 递归-耗时多
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int f1 = 1, f2 = 2;
        if (n == 1) {
            return f1;
        }
        if (n == 2) {
            return f2;
        }
        for (int i = 2; i < n; i++) {
            int tem = f2;
            f2 += f1;
            f1 = tem;
        }
        return f2;
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode resHead = new ListNode();
        ListNode resCur = new ListNode();
        resHead.next = resCur;

        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val != 0) {
                resCur.val += curr.val;
            } else if (curr.next != null) {
                resCur.next = new ListNode();
                resCur = resCur.next;
            }
            curr = curr.next;
        }
        return resHead.next;
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value < 2) {
                continue;
            }
            sum += value * (value - 1) / 2;
        }
        return sum;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = 0;
        int max = nums[0];
        for (int num : nums) {
            if (preSum > 0) {
                preSum += num;
            } else {
                preSum = num;
            }
            max = Math.max(preSum, max);
        }
        return max;
    }

    public boolean canPartition(int[] nums) {
        // 01背包变体
        // 1.求出nums总和sum，奇数直接return false
        // 2.找满足和为sum的子集
        if (nums.length < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(num, maxNum);
        }
        //必须是偶数才行
        if (sum % 2 > 0) {
            return false;
        }
        // 获取指定的值
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        } else if (maxNum == target) {
            return true;
        }
        // 创建二维数组，行：物品索引，列：容量(包括0)
        boolean[][] dp = new boolean[nums.length][target + 1];

        // 补充表格，每行的第一个都可以装进去
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        // 因为前面已经判断的都放的进去
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }

            }
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }

    public boolean isValid(String s) {
        if (s.length() % 2 > 0) {
            return false;
        }
        // 用右括号作为key，对应左括号为value
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // 是后面的
            if (pairs.containsKey(c)) {
                // 判断前面保存的东西
                if (stack.isEmpty() || !Objects.equals(stack.peek(), pairs.get(c))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public void threadLocal() {
        ThreadLocal threadLocal = new ThreadLocal();
    }

}
