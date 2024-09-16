package BOJ_1463_1로만들기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		// dp에 들어갈 숫자 배열
		int[] dp = new int[n+1];
		Arrays.fill(dp, 0);
		
		// 정수가 2일때부터 n일때까지 각각 최소 몇번의 연산이 필요한지 계산
		for (int i = 2; i < n+1; i++) {
			// -1을 하는 상황이라면 - 이전의 연산의 +1회 (기본값)
			dp[i] = dp[i-1] + 1;
			
			// 2로 나누어진다면 (1을 더하기 전, 2를 곱하기 전) 비교하여 작은 값 넣기
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
			// 3로 나누어진다면 (1을 더하기 전, 3를 곱하기 전) 비교하여 작은 값 넣기
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
		}
		
		System.out.println(dp[n]);
	}
}
