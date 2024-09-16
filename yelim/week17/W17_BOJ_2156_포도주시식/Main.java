package W17_BOJ_2156_포도주시식;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 문제) 2156_포도주 시식
		 * 
		 * 잔을 선택하면 마셔야 하고, 윈래 위치로.
		 * 연속으로 놓여 있는 3잔을 모두 마실 수 없음.
		 * 
		 * 최대로 마실 수 있는 포도주의 양
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] dp = new int[n+1];
		
		dp[1] = arr[1];
		
		// n이 2보다 크면
		if (n >= 2) {
			dp[2] = dp[1]+arr[2];
			
			// n이 3보다 크면 점화식 적용
			if (n >= 3) {
				for(int i=3; i<=n; i++) {
					dp[i] = Math.max(dp[i-2]+arr[i], dp[i-3]+arr[i-1]+arr[i]);
					dp[i] = Math.max(dp[i], dp[i-1]);
				}
			}
		}
		
		System.out.println(dp[n]);

	}

}
