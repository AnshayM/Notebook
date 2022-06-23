package pers.anshay.notebook.learn.stackandqueen;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 打开转盘锁(使用队列)
 *
 * @author: Anshay
 * @date: 2019/3/29
 */
public class Solution2 {

    public int openLock(String[] deadEnds, String target) {
        int step = 0;
        boolean[] isVisited = new boolean[1000];
        boolean[] isDead = new boolean[1000];
        for (String deadEnd : deadEnds) {
            int deadNum = Integer.parseInt(deadEnd);
            isDead[deadNum] = true;
        }
        //初始化判断
        if (isDead[0]) {
            return -1;
        }
        if ("0000".equals(target)) {
            return 0;
        }
        isVisited[0] = true;
        Queue<Integer> queue = new LinkedList<>();//生成队列，用来添加所有可能的位置，不分顺序，顺序由varLast和last这两个变量来控制。

        int last = Integer.parseInt(target);
        queue.offer(last);//把target加到队列里
        isVisited[last] = true;//记录每一次尝试的上一步的位置

        int varLast = last;//varLast记录每一步的节点值，last表示varLast确定时的子节点，为了不重复所以last不能和varLast相等
        while (!queue.isEmpty()) {
            while (true) {
                //获取并移除队首元素
                int head = queue.poll();
                //  找到0000退出
                if (head == 0) {
                    return step;
                }
                int[] neighbors = getNeighbor(head);
                for (int i = 0; i < 8; i++) {
                    int num = neighbors[i];
                    if (isDead[num] || isVisited[num]) {
                        continue;
                    }
                    //  如果未遍历过且不是死亡元素，把这个数放入队列
                    queue.offer(num);
                    isVisited[num] = true;
                    last = num;
                }
                //因为每次都加了8个进来，所以 队列里不确定顺序，通过head和前面存的varLast做对比，
                if (head == varLast) {
                    break;
                }
            }
            step++;
            varLast = last;

        }
        return -1;
    }

    //转成int型之后用 +9，+1来取余数，+9相当于-1，还避免了用if来求解。
    public int[] getNeighbor(int num) {
        int[] neighbors = new int[8];
        int a = num % 10;
        int b = (num / 10) % 10;
        int c = (num / 100) % 10;
        int d = (num / 1000) % 10;
        neighbors[0] = d * 1000 + c * 100 + b * 10 + (a + 9) % 10;
        neighbors[1] = d * 1000 + c * 100 + b * 10 + (a + 1) % 10;
        neighbors[2] = d * 1000 + c * 100 + (b + 9) % 10 * 10 + (a + 1) % 10;
        neighbors[3] = d * 1000 + c * 100 + (b + 1) * 10 + a;
        neighbors[4] = d * 1000 + (c + 9) % 10 * 100 + b * 10 + a;
        neighbors[5] = d * 1000 + (c + 1) * 100 + b * 10 + a;
        neighbors[6] = (d + 9) % 10 * 1000 + c * 100 + b * 10 + a;
        neighbors[7] = (d + 1) % 10 * 1000 + c * 100 + b * 10 + a;
        return neighbors;
    }
}
