package W13_BOJ_1915_가장큰정사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 1915_가장 큰 정사각형
		 * 1로 된 칸들 중 가장 큰 정사각형의 크기 구하기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		int[][] dp = new int[n+1][m+1];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if (map[i-1][j-1] == 1) {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
				}
				else {
					dp[i][j] = 0;
				}
			}
		}
		
		int max = 0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max*max);
	}

}
