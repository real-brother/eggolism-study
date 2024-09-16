package W16_BOJ_14940_쉬운최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] result;

	public static void main(String[] args) throws IOException {
		/* 문제) 14940_쉬운 최단거리
		 * 모든 지점에서 목표지점까지의 거리
		 * 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점
		 * 원래 갈 수 없는 땅인 위치는 0을 출력하고, 
		 * 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		result = new int[n][m];
		
		int tr = 0;
		int tc = 0;
		// 입력 받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 2) {
					tr = i;
					tc = j;
				}
			}
		}
		
		bfs(tr, tc);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (!visited[i][j] && map[i][j]==1) {
					result[i][j] = -1;
				}
				System.out.print(result[i][j]+" ");	
			}
			System.out.println();
		}
		

	}
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[n][m];
		visited[r][c] = true;
		
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (nr>=0 && nr<n && nc>=0 && nc<m) {
					if (!visited[nr][nc] && map[nr][nc]==1) {
						q.add(new int[] {nr, nc});
						visited[nr][nc] = true;
						result[nr][nc] = result[cur[0]][cur[1]] + 1;
					}
				}
			}
		}
	}

}
