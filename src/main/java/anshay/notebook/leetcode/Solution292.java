package anshay.notebook.leetcode;

/**
 * Nim 游戏
 * <p>
 * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
 * <p>
 * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 * <p>
 * 思路：要获得胜利，自己倒数第二次拿的时候留下4块石头给对方。然后往前递进。也就是保证每一轮都是少4个石头
 * 巴什博奕，n%(m+1)!=0时，先手总是会赢的
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution292 {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
