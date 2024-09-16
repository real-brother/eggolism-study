package BOJ_1339_단어수학;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, maxWordLength = -1;
	static List<char[]> wordList = new ArrayList<>();

	public static void main(String[] args) {
		n = sc.nextInt();
		sc.nextLine();
		
		// 입력 받으면서 각 알파벳의 자릿수 정보를 함께 저장한다
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			char[] charArr = sc.nextLine().toCharArray();
			for (int j = charArr.length - 1; j >= 0; j--) {
				int pow = (int) Math.pow(10, charArr.length - 1 - j);
				map.put(charArr[j] - 'A', map.getOrDefault(charArr[j] - 'A', 0) + pow);

			}
		}
		// 가장 높은 저릿수를 가지는 알파벳 순대로 높은 수를 부여함
		// 알파벳 자릿수 크기대로 값들을 정렬하고 각 순위에 맞는 숫자를 넣어주면 최대값을 구할 수 있다
		List<Integer> keyList = new ArrayList<>(map.keySet());
		Collections.sort(keyList, (o1, o2) -> (map.get(o1) - map.get(o2)));

		int total = 0;
		int num = 10 - map.size();
		for (int key : keyList) {
			total += map.get(key) * num;
			num++;
		}
		System.out.println(total);
	}
}
