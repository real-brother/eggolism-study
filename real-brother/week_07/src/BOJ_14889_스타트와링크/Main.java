package BOJ_14889_스타트와링크;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[][] ability;

	public static void main(String[] args) {
		n = sc.nextInt();
		ability = new int[n][n];
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			indexList.add(i);
		}
		List<List<Integer>> combList = generateCombinations(indexList, n / 2);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ability[i][j] = sc.nextInt();
			}
		}

		// 사람들을 절반으로 먼저 나누기
		// 각 나눈 사람들을 2개씩 뽑아서 능력치 더하기
		// 각각 케이스마다 차이를 구해서 갱신하기
		int minAbs = Integer.MAX_VALUE;
		for (int i = 0; i < combList.size() / 2; i++) {
			List<Integer> v1 = combList.get(i);
			List<Integer> v2 = combList.get(combList.size() - 1 - i);

			List<List<Integer>> v1Comb = generateCombinations(v1, 2);
			List<List<Integer>> v2Comb = generateCombinations(v2, 2);

			int v1Food = 0;
			int v2Food = 0;

			for (int a = 0; a < v1Comb.size(); a++) {
				List<Integer> rc = v1Comb.get(a);
				int r = rc.get(0);
				int c = rc.get(1);
				v1Food += ability[r][c];
				v1Food += ability[c][r];
			}
			for (int a = 0; a < v2Comb.size(); a++) {
				List<Integer> rc = v2Comb.get(a);
				int r = rc.get(0);
				int c = rc.get(1);
				v2Food += ability[r][c];
				v2Food += ability[c][r];
			}
			int abs = Math.abs(v1Food - v2Food);
			if (minAbs > abs)
				minAbs = abs;
		}

		System.out.println(minAbs);
	}

	// 조합 생성 함수
	public static List<List<Integer>> generateCombinations(List<Integer> nums, int r) {
		List<List<Integer>> result = new ArrayList<>();
		generate(nums, 0, r, new ArrayList<>(), result);
		return result;
	}

	private static void generate(List<Integer> nums, int start, int r, List<Integer> current,
			List<List<Integer>> result) {
		if (r == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		for (int i = start; i < nums.size(); i++) {
			current.add(nums.get(i));
			generate(nums, i + 1, r - 1, current, result);
			current.remove(current.size() - 1);
		}
	}
}