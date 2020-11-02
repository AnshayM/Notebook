package anshay.notebook.leetcode.unsolved;

import java.util.*;

/**
 * 140. 单词拆分 II
 * 困难，跳过
 * <p>
 * 对于字符串 ss，如果某个前缀是单词列表中的单词，则拆分出该单词，然后对 ss 的剩余部分继续拆分。
 * 如果可以将整个字符串 ss 拆分成单词列表中的单词，则得到一个句子。在对 ss 的剩余部分拆分得到一个句子之后，
 * 将拆分出的第一个单词（即 ss 的前缀）添加到句子的头部，即可得到一个完整的句子。上述过程可以通过回溯实现。
 * <p>
 * 假设字符串 ss 的长度为 nn，回溯的时间复杂度在最坏情况下高达 O(n^n)O(n
 * n
 * )。时间复杂度高的原因是存在大量重复计算，可以通过记忆化的方式降低时间复杂度。
 * <p>
 * 具体做法是，使用哈希表存储字符串 ss 的每个下标和从该下标开始的部分可以组成的句子列表，
 * 在回溯过程中如果遇到已经访问过的下标，则可以直接从哈希表得到结果，而不需要重复计算。如果到某个下标发现无法匹配，
 * 则哈希表中该下标对应的是空列表，因此可以对不能拆分的情况进行剪枝优化。
 * <p>
 * 还有一个可优化之处为使用哈希集合存储单词列表中的单词，这样在判断一个字符串是否是单词列表中的单词时只需要判断该字符串是否在哈希集合中即可，
 * 而不再需要遍历单词列表。
 */
class Solution216 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
        List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
        List<String> breakList = new LinkedList<String>();
        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        if (!map.containsKey(index)) {
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if (index == length) {
                wordBreaks.add(new LinkedList<String>());
            }
            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }
}