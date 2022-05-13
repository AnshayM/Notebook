package anshay.notebook.hw;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 通过键盘输入一串小写字母(a~z)组成的字符串。请编写一个字符串过滤程序，若字符串中出现多个相同的字符，将非首次出现的字符过滤掉。
 * 比如字符串“abacacde”过滤结果为“abcde”
 *
 * @author machao
 * @date 2022/5/13
 */
public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String a = sc.nextLine();
			StringFilter(a);

		}
	}


	public static String StringFilter(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		Set<Character> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (set.add(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
}
