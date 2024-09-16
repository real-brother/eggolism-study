package W25_BOJ_1987_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[] visited;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		/* 문제) 알파벳
		 * 
		 * 상하좌우로 이동 가능한 맵에서 알파벳이 겹치지 않고 최대로 갈 수 있는 칸 수 구하기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[26];
		
		for(int i=0; i<n; i++) {
			String tmp = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = tmp.charAt(j)- 'A';
			}
		}
		
		// 처음 시작부터 카운트
		dfs(0, 0, 1);
		
		System.out.println(max);
		
	}
	
	static void dfs(int r, int c, int cnt) {
		// 방문처리
		visited[map[r][c]] = true;
		// 최대값 갱신
		max = Math.max(max, cnt);
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 범위 내에 있고 겹치지 않는 알파벳이면
			if(nr>=0 && nr<n && nc>=0 && nc<m) {
				if (!visited[map[nr][nc]]) {
					dfs(nr, nc, cnt+1);
				}
				

			}
		}
		// 방문배열 초기화 - 이전 상태로 돌아가기
		visited[map[r][c]] = false;
	}

}
