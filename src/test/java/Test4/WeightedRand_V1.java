package Test4;

import java.util.Random;

/**
 * @author machao
 * @date 2022/5/26
 */
public class WeightedRand_V1 {
	int[] count = null;
	int[] value = null;
	int total = 0;

	public WeightedRand_V1(int[] input) {
		this.value = input;
		count = new int[value.length];
		count[0] = value[0];
		for (int i = 1; i < value.length; i++) {
			count[i] = count[i - 1] + value[i];
		}
		total = count[count.length - 1];
	}

	public int next() {
		Random random = new Random();
		int target = random.nextInt(total) + 1;
		for (int i = 0; i < count.length; i++) {
			if (target <= count[i]) {
				return i;
			}
		}
		return 0;
	}
}
