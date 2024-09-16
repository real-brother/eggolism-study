package BOJ_1463_1로만들기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// N까지의 숫자를 저장할 배열을 만든다
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		
		// 초기값을 채워준다
		dp[1] = 0;
		
		//앞에서부터 배열을 채워나간다.
		if(N >= 2) {
			for(int i = 2; i <= N; i++) {
				//3과 2로 나눠지면 -> 6의 배수이면 2로 나눈 거, 3으로 나눈 거, 1 뺀 거 중에 최대값을 가져온다. 
				if(i % 3 == 0 && i % 2 == 0) {
					dp[i] = Math.min(dp[i / 3] + 1, Math.min(dp[i / 2] + 1, dp[i - 1] + 1));
				}
				// 3의 배수이면 3으로 나눈 거와 1 뺀 거 중 최대값을 가져온다.
				else if(i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);
				// 2의 배수이면 2로 나눈 거와 1 뺀 거 중 최대값을 가져온다.
				else if(i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);
				// 둘 다 아니라면 1 뺀 거를 가져온다.
				else dp[i] =  dp[i - 1] + 1;
			
			}
		}
		
		System.out.println(dp[N]);
	}
	
}