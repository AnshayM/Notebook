package test2;

import java.util.Scanner;

/**
 * @author machao
 * @date 2022/5/25
 */
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num = in.nextInt();
			int source[] = new int[num];
			for (int i = 0; i < num; i++) {
				source[i] = in.nextInt();
			}
			System.out.println(doCul(source));

		}
	}

	private static int doCul(int[] w) {


		return 0;
	}

	private static int shortAdd(int num1, int num2) {
		int sum = 0;
		int i = 0;
		while (num1 > 0 || num2 > 0) {
			int cur = (num1 % 2 + num2 % 2) % 2;
			sum += cur << i;
			i++;
			num1 = num1 >> 1;
			num2 = num2 >> 1;
		}
		return sum;
	}
}
