package W3_BOJ_17086_아기상어2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int graph[][];
	static boolean visited[][];
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	

	public static void main(String[] args) throws IOException {
		/* 문제) 아기상어2
		 * 어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와의 거리이다. 
		 * 두 칸의 거리는 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수이고, 
		 * 이동은 인접한 8방향(대각선 포함)이 가능하다. 안전 거리가 가장 큰 칸을 구해보자. 
		 * 0은 빈 칸, 1은 아기 상어가 있는 칸이다. 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (graph[i][j]==0) {
					// bfs 호출하여 반환된 값(거리)를 저장
					int tmp = bfs(i, j);
					// 값이 더 큰 지 검사
					if (tmp > max) {
						max = tmp;
					}
				}
			}
		}
		
		System.out.println(max);

	}
	
	
	//bfs 메소드
	public static int bfs(int x, int y) {
		visited = new boolean[n][m];	// 초기화
		Queue<int[]> q = new LinkedList<>();
		
		// 큐에 x,y 좌표와 현재까지의 거리를 저장
		q.add(new int[] {x, y, 0});
		visited[x][y] = true;	// 방문했음을 체크
		
		// 큐에 요소가 없을 때까지
		while (!q.isEmpty()) {
			int[] cur = q.poll();	// 큐의 맨 앞에 있는 값 빼주기
			
			// 델타 탐색하면서 검사
			for(int d=0; d<8; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				int val = cur[2];
				
				// 범위 벗어나면 
				if (nr<0 || nr>=n || nc<0 || nc>=m) {
					continue;
				}
				// 방문한 적이 있으면
				if (visited[nr][nc]==true) {
					continue;
				}
				// 상어가 있는 곳이면 거리에 +1 하고 반환
				if (graph[nr][nc]==1) {
					return val+1;
				}
				// 방문 표시
				visited[nr][nc] = true;
				// 큐에 nr, nc 좌표와 거리+1 값 저장
				q.add(new int[] {nr, nc, val+1});
			}
		}
		return 0;
	}
			
	
}
