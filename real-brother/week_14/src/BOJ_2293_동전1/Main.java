package BOJ_2293_동전1;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, k;
	static int[] numList, dp;
	
	/*
	 * 동전 가지수를 하나씩 늘려가면서 dp를 쌓아가면 된다.
	 */
	
	public static void main(String[] args) {
		n = sc.nextInt();
		k = sc.nextInt();
		numList = new int[n];
		dp = new int[k + 1];
		
		for (int i = 0; i < n; i++) {
			numList[i] = sc.nextInt();
		}
		
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j >= numList[i])
					dp[j] += dp[j - numList[i]];
			}
		}
		
		System.out.println(dp[k]);
	}
}