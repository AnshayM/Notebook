import java.util.*;
import java.util.stream.Collectors;

/**
 * @author machao
 * @date 2022/5/25
 */
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> list = new ArrayList<>();

		while (in.hasNext()) {
			String s = in.next();
			if (s.contains(".")) {
				list.add(s);
			} else {
				int n = Integer.parseInt(s);
				String res = culTopN(list, n);
				System.out.println(res);
			}
		}
	}

	public static String culTopN(List<String> list, int n) {
		HashMap<String, Integer> map = new HashMap<>();
		for (String str : list) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted((v1, v2) -> {
			if (v1.getValue().equals(v2.getValue())) {
				return v1.getKey().compareTo(v2.getKey());
			} else {
				return -v1.getValue().compareTo(v2.getValue());
			}
		}).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(collect.get(i).getKey());
		}
		return sb.toString();
	}
}
