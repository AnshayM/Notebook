package pers.anshay.notebook;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int[][] kClosest(int[][] points, int K) {


        Arrays.sort(points, (v1, v2) -> v1[0] * v1[0] - v2[0] * v2[0] + v1[1] * v1[1] - v2[1] * v2[1]);
        return Arrays.copyOf(points, K);
    }

    public static void main(String[] args) {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }

}
