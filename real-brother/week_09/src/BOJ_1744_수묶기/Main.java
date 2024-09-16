package BOJ_1744_수묶기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static List<Integer> positiveNumList = new ArrayList<Integer>();
	static List<Integer> negativeNumList = new ArrayList<Integer>();
	static int answer = 0;

	public static void main(String[] args) {
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int temp = sc.nextInt();
			if (temp > 0)	positiveNumList.add(temp);
			else if (temp <= 0)	negativeNumList.add(temp);
		}
		// 양수, 음수 따로 입력 받기
		// 양수를 내림차순으로 정렬해서 2개씩 묶어서 곱해주기
		// 1이 나온다면 그냥 더하기
		Collections.sort(positiveNumList, Collections.reverseOrder());
		for (int i = 0; i < positiveNumList.size(); i++) {
			// 맨 끝의 수가 아니라면
			if (i < positiveNumList.size() - 1) {
				if (positiveNumList.get(i + 1) == 1 || positiveNumList.get(i) == 1) {
					answer += positiveNumList.get(i);
					answer += positiveNumList.get(i + 1);
					i++;
				} else {
					answer += positiveNumList.get(i) * positiveNumList.get(i + 1);
					i++;
				}

			} else {
				answer += positiveNumList.get(i);
			}
		}

		// 음수를 오름차순으로 정렬
		// 2개씩 묶어서 곱해주기 -> 양수로 만들자
		Collections.sort(negativeNumList);
		for (int i = 0; i < negativeNumList.size(); i++) {
			// 맨 끝의 수가 아니라면
			if (i < negativeNumList.size() - 1) {
				answer += negativeNumList.get(i) * negativeNumList.get(i + 1);
				i++;
			} else {
				answer += negativeNumList.get(i);
			}

		}
		System.out.println(answer);
	}
}