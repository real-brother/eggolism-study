package W14_BOJ_2293_동전1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 문제) 2293_ 동전1
		 * 
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] coin = new int[n+1];
		for(int i=1; i<=n; i++) {
			coin[i] = sc.nextInt();
		}
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		
		for(int i=1; i<=n; i++) {
			// 동전 순서대로 크기 늘리면서 dp값 갱신해주기
			for(int j=coin[i]; j<=k; j++) {
				dp[j] = dp[j] + dp[j-coin[i]];
			}
		}
		
		System.out.println(dp[k]);

	}

}
