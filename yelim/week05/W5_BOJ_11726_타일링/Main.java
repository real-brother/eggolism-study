package W5_BOJ_11726_타일링;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		/* 문제)
		 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
		 */
		
		/* 설계)
		 * 2*1의 경우의 수 = 1
		 * 2*2의 경우의 수 = 2
		 * 2*3의 경우의 수 = 2*2 + 2*1
		 * 2*4의 경우의 수 = 2*3 + 2*2
		 * 타일이 n개일 때, dp[n] = dp[n-1]+dp[n-2]
		 */
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<=n; i++) {
			dp[i] = (dp[i-1]+dp[i-2]) % 10007;
		}
		System.out.println(dp[n]);
		
		
	}

}
