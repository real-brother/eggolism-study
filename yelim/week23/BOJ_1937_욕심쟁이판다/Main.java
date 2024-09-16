package WEEK_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이판다 {
	
	static int n;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] dp;
	static int ans = 1;

	public static void main(String[] args) throws IOException {
		/* 문제) 1937_욕심쟁이 판다
		 * 
		 * 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동
		 * 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.
		 * 최대한 많은 칸을 이동하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.
		 * 
		 * 판다가 이동할 수 있는 칸의 수의 최댓값을 출력
		 * 
		 * => dfs는 시간초과 => dfs + dp
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		dp = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		// 방문하지 않은 곳, dp값이 -1인 좌표에서 dfs 돌기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if (dp[i][j] == -1) {
					dfs(i, j);
				}
			}
		}
		
		System.out.println(ans);

	}
	
	static int dfs(int r, int c) {
		// 이미 이동횟수가 저장되어 있으면 해당 값 반환
		if (dp[r][c] != -1) {
			return dp[r][c];
		}
		
		// 1로 초기화 (방문함)
		dp[r][c] = 1;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<n) {
				if(map[nr][nc]>map[r][c]) {
					// 현재 위치의 dp 값과 탐색 후 값 비교해서 dp 값 갱신
					dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
					
					// 정답의 최대값 갱신
					ans = Math.max(ans, dp[r][c]);
				}
			}
		}
		return dp[r][c];
	}

}
