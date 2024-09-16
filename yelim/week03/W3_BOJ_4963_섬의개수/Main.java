package W3_BOJ_4963_섬의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int w;
	static int h;
	static int[][] graph;
	static boolean[][] visited;
	// 상하좌우와 대각선까지의 델타배열
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	

	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
		 * 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
		 * 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
		 * 
		 * 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
		 * w와 h는 50보다 작거나 같은 양의 정수이다.
		 * 
		 * 섬의 개수 출력
		 */
		
		
		/* 설계)
		 * 1이 이어져있는데까지 한개로 침!
		 * 상하좌우, 대각선까지 델타배열 만들고, 
		 * 그래프 배열 탐색하면서 1이고, 방문한 적이 없으면 해당 지점에서 dfs 호출
		 * 
		 * DFS:
		 * 방문 체크 하고, 델타배열로 상하좌우 이동하면서 배열 범위 벗어나는지, 
		 * 방문한 적이 있는지, 길이 있는지 확인하고 재귀호출하여 반복
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());	// 지도 너비
			h = Integer.parseInt(st.nextToken());	// 지도 높이
			
			// 둘 다 0으로 입력 받으면 벗어나기
			if (w==0 && h==0) {
				break;
			}
			
			graph = new int[h][w];
			visited = new boolean[h][w];
			
			// 값 입력받고 저장
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 섬의 개수 저장할 변수 초기화
			int cnt = 0;
			
			// 배열 탐색하면서 1이고, 방문한 적이 없으면 DFS 호출, 섬의 개수 1 증가
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if (graph[i][j]==1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			
		}
		

	}
	
	// DFS 메소드
	public static void dfs(int x, int y) {
		visited[x][y] = true;	// 방문 체크
		
		// 델타배열 이용하여 이동하면서 갈 수 있는 길인지 체크해주기
		for(int d=0; d<8; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			if (nr>=0 && nr<h && nc>=0 && nc<w && !visited[nr][nc] && graph[nr][nc]==1) {
				dfs(nr, nc);
			}
		}
	}

}
