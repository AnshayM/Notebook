package anshay.notebook.test;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author machao
 * @date 2022/5/29
 */
public class HwTest {
	/*所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
	例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
	编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
	示例 1：

	输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
	输出：["AAAAACCCCC","CCCCCAAAAA"]
	示例 2：

	输入：s = "AAAAAAAAAAAAA"
	输出：["AAAAAAAAAA"]


	提示：

	0 <= s.length <= 105
	s[i] 为 'A'、'C'、'G' 或 'T'*/
	public static void main(String[] args) {
		String aa = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		List<String> repeatedDnaSequences = findRepeatedDnaSequences2(aa);
		System.out.println(repeatedDnaSequences);
	}


	public static List<String> findRepeatedDnaSequences(String s) {
		// 长度为10，str.substring(0,10)
		// 暴力解法，从头遍历，每次对去掉首字符后的做contains判断
		// HashMap<String, Integer> map = new HashMap<>();
		// // 从i到j
		// int[][] dp = new int[][]{};
		//
		// if (StringUtils.isEmpty(s) || s.length() < 11) {
		// 	return new ArrayList<>();
		// }
		// for (int i = 0; i < s.length() - 11; i++) {
		// 	String target = s.substring(i, i + 10);
		// 	String substring = s.substring(i + 1);
		// 	//
		// 	if (substring.contains(target)) {
		// 		result.add(target);
		// 	}
		// }
		// return new ArrayList<>(result);
		return null;
	}

	public static List<String> findRepeatedDnaSequences2(String s) {
		HashMap<String, Integer> map = new HashMap<>();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < s.length() - 10; i++) {
			String target = s.substring(i, i + 10);
			Integer integer = map.getOrDefault(target, 0);
			if (integer == 1) {
				result.add(target);
			}
			map.put(target, integer + 1);
		}
		return result;
	}


}
