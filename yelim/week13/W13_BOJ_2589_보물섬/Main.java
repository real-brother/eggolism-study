package W13_BOJ_2589_보물섬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int ans;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 2589_보물섬
		 * L: 육지, W: 바다
		 * 상하좌우 이웃한 육지로만 이동 가능, 한 칸 이동에 한 시간
		 * 보물은 최단거리로 갈 수 있는 가장 긴 시간이 걸리는 두 곳에 묻혀있음
		 * 보물이 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 육지 완탐
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
					
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	// 가장 먼 두 곳의 길이 구하면 됨
	static void bfs(int r, int c) {
		visited = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();
		
		visited[r][c] = true;
		// 좌표(r, c), 시간
		q.add(new int[] {r, c, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 내에 있고, 방문하지 않았고, 해당 좌표가 육지라면
				if (nr>=0 && nr<n && nc>=0 && nc<m) {
					if (!visited[nr][nc] && map[nr][nc]=='L') {
						visited[nr][nc] = true;
						// 시간 1 더해주면서 큐에 넣어주기
						q.add(new int[] {nr, nc, cur[2]+1});
						// 최대값 갱신
						ans = Math.max(ans, cur[2]+1);
					}
				}
			}
		}
		
	}

}
