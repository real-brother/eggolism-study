package BOJ_16637_괄호추가하기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static char[] expression;
	static List<Integer> numList = new ArrayList<Integer>();
	static List<Character> operList = new ArrayList<Character>();
	
	static int n, result = Integer.MIN_VALUE; // 결과값을 최솟값으로 설정해야함

	public static void main(String[] args) {
		n = sc.nextInt();
		sc.nextLine();
		expression = sc.nextLine().toCharArray();
		
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) numList.add((int) expression[i] - 48);
			else operList.add(expression[i]);
		}
		// dfs를 활용하여 각 연산을 진행하기
		dfs(0, numList.get(0));
		System.out.println(result);
	}
	
	private static void dfs(int index, int total) {
		// 끝까지 갔다면 연산 끝
		if (index == operList.size()) {
			result = Math.max(result, total);
			return;
		}
		// 괄호를 사용하지 않는 경우 -> 한번의 연산 후 넘어가기
		int nowTotal = calculate(total, numList.get(index + 1), operList.get(index));
		dfs(index + 1, nowTotal);

		// 괄호를 사용하는 경우 -> 두번의 연산 하고 넘어가기
		if (index + 2 <= numList.size() - 1) { // 범위 안이라면
			int tempCal = calculate(numList.get(index + 1), numList.get(index + 2), operList.get(index + 1));
			nowTotal = calculate(total, tempCal, operList.get(index));
			dfs(index + 2, nowTotal);
		}
	}

	private static int calculate(int a, int b, char oper) {
			if (oper == '-') return a - b;
			else if (oper == '+') return a + b;
			else if (oper == '*') return a * b;
			else return 0;
	}
}