package pers.anshay.notebook.test;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author machao
 * @date 2022/6/25
 */
public class Hw3 {
	/*加减法算式
输入String（只有数字、加减号，以及多余的空格）
例如：
输入：
11+ 12 + 13  - 6
输出：30

输入：
-10-20+30
输出：0*/
	public static void main(String[] args) {
		//先调试一下
		String str = "-10-20+30";
		Integer cul = cul(str);
		System.out.println(cul);

	}


	private static final Character REDUCE = '-';
	private static final Character ADD = '+';

	public static Integer cul(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		//结果
		int sum = 0;
		//当前要执行的符号
		int curFlag = 1;
		//当前的数字
		int currentNum = 0;
		// 支持首个字符是符号
		for (char c : string.toCharArray()) {
			if (' ' == c) {
				continue;
			}
			//数字
			if (c >= '0' && c <= '9') {
				currentNum = currentNum * 10 + c - '0';
				continue;
			}
			//遇到符号，进行计算
			sum += curFlag * currentNum;
			// 还原符号和currentNum
			curFlag = 1;
			currentNum = 0;

			if (REDUCE.equals(c)) {
				curFlag = -1;
			}
		}
		// 循环结束后，最后一个数字需要处理
		return sum + curFlag * currentNum;
	}

	public  void main2(String[] args) {
		String input = "-2+3-1+100";
		input = input.replaceAll("-", "+-");
		System.out.println(input);
		int sum = 0;
		for (String s : input.split("\\+")) {
			System.out.println(s);
			try {
				sum += Integer.parseInt(s);
			} catch (
					Exception ex) {
				ex.printStackTrace();
				System.out.println(ex);
			}
		}
		System.out.println("sum=" + sum);
		boolean equals = Objects.equals(1, 2);
		boolean equals1 = input.equals(input.toLowerCase());
	}

}
