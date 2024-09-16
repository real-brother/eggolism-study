package BOJ_1932_정수삼각형;

import java.util.Scanner;

public class Main {
	static int[][] arr;
	static Integer[][] dp;
	static int N;
	static Scanner sc = new Scanner(System.in);
 
	public static void main(String[] args) {
		N = sc.nextInt();
		arr = new int[N][N];
		dp = new Integer[N][N];
        
		// 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 가장 밑의 줄에 초기값 세팅
		for (int i = 0; i < N; i++) {
			dp[N - 1][i] = arr[N - 1][i];
		}
		int answer = findDP(0, 0);
		System.out.println(answer);
	}
 
	
	static int findDP(int depth, int idx) {
		// 마지막 행일 경우 현재 위치의 dp값 반환
		if(depth == N - 1) return dp[depth][idx];
 
		// 탐색하지 않았던 값일 경우 다음 행의 양쪽 값 비교
		if (dp[depth][idx] == null) {
			dp[depth][idx] = Math.max(findDP(depth + 1, idx), findDP(depth + 1, idx + 1)) + arr[depth][idx];
		}
		
		return dp[depth][idx];
	}
}