package BOJ_2470_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 수의 개수 입력
		int n = Integer.parseInt(br.readLine());
		String numStr = br.readLine();
		String[] numArrStr = numStr.split(" "); // 수열을 공백을 기준으로 분리하여 배열로 만듦
		// 수열 입력
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
		    int num = Integer.parseInt(numArrStr[i]); // 문자열을 정수로 변환
		    numbers.add(num); // 리스트에 정수 추가
		}

		numbers.sort(null);

		int[] leftRightIndex = countGoodNumbers(n, numbers);
		int ans_1 = numbers.get(leftRightIndex[0]);
		int ans_2 = numbers.get(leftRightIndex[1]);

//		System.out.println(numbers);
		// 결과 출력
		System.out.println(ans_1 + " " + ans_2);

	}

	private static int[] countGoodNumbers(int n, List<Integer> numbers) {
		int[] leftRightIndex = new int[2];

		int left = 0;
		int right = n-1;
		int minDiff = Integer.MAX_VALUE;
		while (left < right) {
			int sum = numbers.get(left) + numbers.get(right);
			int absDiff = Math.abs(sum);
			
			if (absDiff < minDiff) {
				minDiff = absDiff;
				leftRightIndex[0] = left;
				leftRightIndex[1] = right;
			} else if (sum > 0) {
				right--;
			} else {
				left++;
			}

		}
		
		return leftRightIndex;
	}
}