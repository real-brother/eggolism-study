package BOJ_2805_나무자르기;

import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		// 수의 개수 입력
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		List<Integer> treeLengthList = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			treeLengthList.add(sc.nextInt()); // 리스트에 정수 추가
		}

		treeLengthList.sort(null);

		long ans = findPossibleMaxHeight(treeLengthList, m);

//		System.out.println(numbers);
		// 결과 출력
		System.out.println(ans);

	}

	private static long findPossibleMaxHeight(List<Integer> numbers, int m) {
		long result = 0;
		long left = 0;
		long right = findMaxHeight(numbers)-1;
		long mid = -1;
		while (left <= right) {

			mid = (left + right) / 2;
			long sum = 0;
			for (long height : numbers) {
				if (height - mid > 0) {
					sum += (height - mid);
				}
			}

			if (sum < m) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}

		return right;
	}

	private static int findMaxHeight(List<Integer> numbers) {
		int result = Integer.MIN_VALUE;
		for (int num : numbers) {
			if (result < num) {
				result = num;
			}
		}
		return result;
	}
}
