package pers.anshay.notebook.algorithm.hw;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 通过键盘输入一串小写字母(a~z)组成的字符串。请编写一个字符串压缩程序，将字符串中连续出席的重复字母进行压缩，并输出压缩后的字符串。
 * 压缩规则：
 * 1、仅压缩连续重复出现的字符。比如字符串"abcbc"由于无连续重复字符，压缩后的字符串还是"abcbc"。
 * 2、压缩字段的格式为"字符重复的次数+字符"。例如：字符串"xxxyyyyyyz"压缩后就成为"3x6yz"。
 *
 * @author machao
 * @date 2022/5/13
 */
public class Practice2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String a = sc.nextLine();
			StringFilter(a);

		}
	}

	private static String  StringFilter(String a) {
		// 用来计算是否出现过
		if (StringUtils.isBlank(a)) {
			return a;
		}
		StringBuilder sb = new StringBuilder();

		return null;
	}
}
