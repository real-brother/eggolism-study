package BOJ_14501_퇴사;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[] t;
	static int[] p;
	
	public static void main(String[] args) {
		n = sc.nextInt();
		t = new int[n]; // 소요 기간
		p = new int[n]; // 금액
		
		for (int i = 0; i < n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
		}

		int[] dp = new int[n + 1];
		// 모든 일자를 탐색하면
		for (int i = 0; i < n; i++) {
			//새로운 일이 퇴사일 이전에 있다면
			if (i + t[i] <= n) { 
				// 그 일을 마무리하는 일에 반영해 최대값을 갱신
				dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]); 
			} 
			// 이전 상태에서의 최대 이익을 고려하기
			dp[i + 1] = Math.max(dp[i + 1], dp[i]); 
		}
		System.out.println(dp[n]);
	}
}