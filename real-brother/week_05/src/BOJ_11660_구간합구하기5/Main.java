package BOJ_11660_구간합구하기5;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] numList = new int[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				numList[i][j] = sc.nextInt();
			}
		}
		// dp로 누적합 구하기
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + numList[i][j];
			}
		}

		for (int t = 0; t < m; t++) {
			int r1 = sc.nextInt();
			int c1 = sc.nextInt();
			int r2 = sc.nextInt();
			int c2 = sc.nextInt();
			
			// 누적합을 이용하여 부분합을 구하기
			int sum = dp[r2][c2] - dp[r2][c1-1] - dp[r1-1][c2] + dp[r1-1][c1-1];
			System.out.println(sum);
		}
	}
}
