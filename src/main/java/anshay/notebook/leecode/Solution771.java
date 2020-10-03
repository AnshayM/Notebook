package anshay.notebook.leecode;

/**
 * 宝石与石头
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头
 * <p>
 * 可以考虑用一个boolean[]来存前一个的记录，然后比对后面字符串的值
 * 或者用一个int[]存储对应的ascll码
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution771 {
    public static void main(String[] args) {
        int n = numJewelsInStones("abc", "poiucbacba");
        System.out.println(n);
    }

    public static int numJewelsInStones(String J, String S) {
        if (J == null || "".equals(J) || S == null | "".equals(S)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (J.indexOf(S.charAt(i)) > -1) {
                count++;
            }
        }
        return count;
    }

    public static int numJewelsInStones2(String J, String S) {
        // 未赋值的都默认为0
        int[] lowerCaseList = new int[26];
        int[] upperCaseList = new int[26];
        int num = 0;
        // 主要是在S中找J存在的字符，并且S重复数目也要算上，所以先遍历S
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                lowerCaseList[ch - 'a']++;
            } else {
                upperCaseList[ch - 'A']++;
            }
        }
        for (int i = 0; i < J.length(); i++) {
            char ch = J.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                num += lowerCaseList[ch - 'a'];
            } else {
                num += upperCaseList[ch - 'A'];
            }
        }
        return num;
    }

    public static int numJewelsInStones1(String J, String S) {
        if (J == null || "".equals(J) || S == null | "".equals(S)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            char ch = J.charAt(i);
            while (S.indexOf(ch) > -1) {
                count++;
                int index = S.indexOf(ch);
                S = S.substring(0, index) + S.substring(index + 1);
            }
        }

        return count;
    }

}
