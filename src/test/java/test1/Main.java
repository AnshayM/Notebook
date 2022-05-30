package test1;


import java.util.Scanner;

/**
 * 5 5
 * HELLOWORLD
 * CPUCY
 * EKLQH
 * CHELL
 * LROWO
 * DGRBC
 *
 * @author machao
 * @date 2022/5/25
 */
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int m = in.nextInt();
			int n = in.nextInt();
			char[][] arr = new char[m][n];
			String target = in.next();
			int i = 0;
			while (i < m) {
				arr[i++] = in.next().toCharArray();
			}
			boolean[][] unableTo = new boolean[m][n];

			String s = doCheck(arr, target, unableTo);
			System.out.println(s);
		}
	}

	public static String doCheck(char[][] arr, String target, boolean[][] unableTo) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				String res = doCheck(arr, i, j, target, unableTo);
				if (!"NO".equals(res)) {
					return res;
				}
			}
		}
		return "NO";
	}

	public static String doCheck(char[][] arr, int i, int j, String target, boolean[][] unableTo) {
		char c = arr[i][j];
		int length = target.length();
		if (c != target.charAt(length - 1)) {
			return "NO";
		}
		if (length == 1) {
			return String.valueOf(i + 1).concat(" ").concat(String.valueOf(j + 1));
		}
		unableTo[i][j] = true;
		String nextTarget = target.substring(0, length - 1);
		if (i > 0 && !unableTo[i - 1][j]) {
			String res = doCheck(arr, i - 1, j, nextTarget, unableTo);
			if (!"NO".equals(res)) {
				return res;
			}
		}
		if (i < arr.length - 1 && !unableTo[i + 1][j]) {
			String res = doCheck(arr, i + 1, j, nextTarget, unableTo);
			if (!"NO".equals(res)) {
				return res;
			}
		}
		if (j > 0 && !unableTo[i][j - 1]) {
			String res = doCheck(arr, i, j - 1, nextTarget, unableTo);
			if (!"NO".equals(res)) {
				return res;
			}
		}
		if (j < arr[0].length - 1 && !unableTo[i][j + 1]) {
			String res = doCheck(arr, i, j + 1, nextTarget, unableTo);
			if (!"NO".equals(res)) {
				return res;
			}
		}

		return "NO";
	}
}
