package W8_BOJ_2573_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ice;	// 빙산 개수
//	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		/* 문제) 빙산
		 * 산의 각 부분에 해당되는 칸에 있는 높이는 
		 * 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다
		 * 
		 * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연도 변수
		int year = 0;
		
		while(true) {
			visited = new boolean[n][m];
			
			// 빙산 개수
			ice = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if (!visited[i][j] && graph[i][j]!=0) {
						countDfs(i, j);
						ice++;
					}
				}
			}
			
			// 빙산이 2개 이상일 경우
			if (ice >= 2) {
				break;
			}
			// 빙산이 다 녹았을 경우
			else if (ice==0) {
				year = 0;
				break;
			}
			
			bfs();
			year++;
		}
		
		System.out.println(year);
		
		
	}
	// 빙산 녹이는 bfs
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (graph[i][j] > 0) {
					q.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cnt = 0;	// 0 개수 세서 저장할 변수
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (nr>=0 && nr<n && nc>=0 && nc<m) {
					if (!visited[nr][nc] && graph[nr][nc]==0) {
						cnt++;
					}
				}
			}
			
			// 0개수를 뺐을 때 음수이면 0저장
			if (graph[cur[0]][cur[1]]-cnt < 0) {
				graph[cur[0]][cur[1]] = 0;
			}
			// 아니면 뺀 값 저장
			else {
				graph[cur[0]][cur[1]] -= cnt;
			}
		}
		
	}
	
	
	// 빙산 개수 구할 dfs
	static void countDfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<m) {
				if (!visited[nr][nc] && graph[nr][nc]>0) {
					countDfs(nr, nc);
				}
			}
		}
	}

}
