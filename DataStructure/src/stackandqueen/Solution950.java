package stackandqueen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 按递增顺序显示卡牌
 * <p>
 * 牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。
 * 最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。
 * 现在，重复执行以下步骤，直到显示所有卡牌为止：
 * 1从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
 * 2如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
 * 3如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
 * 返回能以递增顺序显示卡牌的牌组顺序。
 * 答案中的第一张牌被认为处于牌堆顶部。
 *
 * @author: Anshay
 * @date: 2019/4/30
 */
public class Solution950 {
    public static void main(String[] args) {
        int[] a = deckRevealedIncreasing(new int[]{17, 13, 11, 2, 3, 5, 7});
    }

    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null || deck.length == 0) {
            return deck;
        }
        // 先排序，然后再倒推
        Arrays.sort(deck);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(deck[deck.length - 1]);
        for (int i = deck.length - 2; i >= 0; i--) {
            queue.offer((queue.poll()));
            queue.offer(deck[i]);
        }

        // 这里得到的是一个反序的队列
        int i = deck.length - 1;
        while (!queue.isEmpty()) {
            deck[i--] = queue.poll();
        }
        return deck;
    }

}
