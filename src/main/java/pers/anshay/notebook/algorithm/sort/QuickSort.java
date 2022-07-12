package pers.anshay.notebook.algorithm.sort;

import java.util.Random;

/**
 * @author: Anshay
 * @date: 2019/4/26
 */

public class QuickSort {
    /*
     * 当待排序的数的数量较少时，使用简单插入排序的实际耗时会小于快速排序，这是无数人经过实际测试或实验得出的结论，
     * 至于原因应该是当数量较少时，使用递归所需的反复调用函数所增加的时间超过了时间复杂度降低所减少的时间。
     * Cutoff可以认为是代码中对于“较少”和“较多”的分界线，较多时使用快速排序，较少时使用InsertionSort（插入排序）
     */
    static int CUTOFF = 3;// 快排和插入排序的分界值

    public static void main(String[] ars) {
        int[] a = new int[10];// 定义一个长度为10的数组a。
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);// 每个字符为0到100之间的随机数
        }
        print(a);
        quickSort(a, 0, a.length - 1);
        print(a);

    }

    // 快排的主要程序
    public static void quickSort(int[] a, int left, int right) {
        if (left + CUTOFF <= right) {// 当两者差异大于3时采用快排，否则采用插入排序
            int pivot = median3(a, left, right);
            //这一步结束后已经确定了left，right-1，right三个的从小到大的顺序

            int i = left, j = right - 1;
            for (; ; ) {
                // 找到第一个不小于枢纽元的元素时停止，这个数索引为i，同时
                while (a[++i] < pivot) {}
                while (a[--j] > pivot) {}// 找到第一个不大于枢纽元的元素时停止，这个数索引为j
                if (i < j)
                    swap(a, i, j);// 如果这两个数是前大后小，则交换这两个数
                else {
                    break;// 否则结束循环，此时已经根据枢纽元分好了
                }
            }

            //把枢纽元换回去，前面i已经加1了，此行运行后，所有枢纽前的都小于枢纽
            swap(a, i, right - 1);
            quickSort(a, left, i - 1);// 递归整理比枢纽元小的那部分排序
            quickSort(a, i + 1, right);// 递归整理比枢纽元大的那部分排序
        } else {
            insertionSort(a, left, right);
        }
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] a, int left, int right) {
        int j;
        for (int i = left + 1; i < right + 1; i++) {
            if (a[i] > a[i - 1]) {
                continue;
            }
            int temp = a[i];
            for (j = i; j > left; j--) {
                //元素后挪
                a[j] = temp;
            }
            a[j] = a[i];
        }
    }

    /**
     * 三数中值分割法
     */
    public static int median3(int[] a, int left, int right) {
        int center = (left + right) / 2;// 左和右之间的中间索引

        if (a[center] < a[left]) {
            swap(a, left, center);
        }
        if (a[right] < a[left]) {
            swap(a, left, right);
        }
        if (a[right] < a[center]) {
            swap(a, center, right);
        }
        // 去中间数，和right-1位置交换，并把枢纽元放在right-1这个位置
        swap(a, center, right - 1);
        return a[right - 1];// 返回预备做枢纽元的元素
    }

    /**
     * 交换数组中两个数据
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 打印方法
    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println(" ");
    }
}
