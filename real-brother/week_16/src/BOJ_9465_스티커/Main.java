package BOJ_9465_스티커;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int T, n;
	static int[][] stickers, dp;
	
	public static void main(String[] args) {
		T = sc.nextInt();
        while (T-- > 0) {
            n = sc.nextInt();
            stickers = new int[2][n];
            dp = new int[2][n];

            // 스티커 점수 입력
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = sc.nextInt();
                }
            }

            // 초기값 설정
            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
            if (n > 1) {
                dp[0][1] = stickers[1][0] + stickers[0][1];
                dp[1][1] = stickers[0][0] + stickers[1][1];
            }

            // 해당 줄에 속하지 않은 n-1, n-2행에 있는 값 중 최대값에 지속해서 누적해주기
            for (int j = 2; j < n; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + stickers[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stickers[1][j];
            }

            // 결과 계산
            int result = Math.max(dp[0][n-1], dp[1][n-1]);
            System.out.println(result);
        }
    }
}
