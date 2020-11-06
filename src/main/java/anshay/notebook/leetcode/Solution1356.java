package anshay.notebook.leetcode;

import java.util.Arrays;

/**
 * 1356根据数字二进制下 1 的数目排序
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 *
 * @author machao
 * @date 2020/11/6
 */
public class Solution1356 {
    public static void main(String[] args) {
        int[] ints = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        int[] ints1 = sortByBits(ints);
    }
    public int[] sortByBits1(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 10000000;
        }
        return map;
    }

    public static int[] sortByBits(int[] arr) {
        int bigPre = 100000;
        for (int i = 0; i < arr.length; i++) {

            int temp = arr[i];
            arr[i] = get(temp) * bigPre + temp;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % bigPre;
        }
        return arr;
    }

    public static int get(int a) {
        int temp =a;

        int count = 0;
        while (a != 0) {
            int i = a % 2;
            if (i == 1) {
                count++;
            }
            a = a / 2;
        }
        int i = Integer.bitCount(temp);
        return count;
        // return Integer.bitCount(a);
    }
}
