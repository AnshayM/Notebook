package Test4;

import java.util.Random;

/**
 * @author machao
 * @date 2022/5/26
 */
public class Main {
	public static void main(String[] args) {
		WeightedRand_V2 v1 = new WeightedRand_V2(new int[]{4, 1, 3, 2});
		for (int i = 0; i < 10; i++) {
			int next = v1.next();
			System.out.println(next);
		}


	}
}
