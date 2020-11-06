package pers.anshay.notebook;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int[] sortByBits(int[] arr) {
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

    public static void main(String[] args) {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }

}
