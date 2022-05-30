import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author machao
 * @date 2022/5/25
 */
public class Test1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		final String input = scanner.nextLine();
		String output = handle(input);
		System.out.println(output);
	}


	public static String handle(String input) {
		if (input == null || input.length() == 0) {
			return null;
		}
		input = input.toLowerCase();
		HashMap<Character, Integer> map = new HashMap<>();
		for (char ch : input.toCharArray()) {
			if (ch < 'a' || ch > 'z') {
				continue;
			}

			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		List<Map.Entry<Character, Integer>> collect = map.entrySet().stream()
				.sorted((v1, v2) -> {
					if (v1.getValue().equals(v2.getValue())) {
						return v1.getKey().compareTo(v2.getKey());
					} else {
						return -v1.getValue().compareTo(v2.getValue());
					}
				}).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Character, Integer> entry : collect) {
			sb.append(entry.getKey());
		}
		return sb.toString();
	}

	// public static String handle(String input) {
	// 	if (input == null || input.length() == 0) {
	// 		return null;
	// 	}
	// 	input = input.toLowerCase();
	// 	int[] array = new int[26];
	// 	for (char ch : input.toCharArray()) {
	// 		if (ch < 'a' || ch > 'z') {
	// 			continue;
	// 		}
	// 		int index = ch - 'a';
	// 		array[index] = array[index] + 1;
	// 	}
	// 	// 数组，次数做下表，二维数组中字符做另一个数组
	//
	// 	int maxIndex = 0;
	// 	int maxValue = -1;
	// 	for (int i = 0; i < array.length; i++) {
	// 		if (array[i] > maxValue) {
	// 			maxIndex = i;
	// 		}
	// 	}
	//
	// 	StringBuilder sb = new StringBuilder();
	//
	// 	return sb.toString();
	// }
}
