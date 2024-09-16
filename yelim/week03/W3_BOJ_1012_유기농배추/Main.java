package W3_BOJ_1012_유기농배추;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int w;
	static int h;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 
		 * 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
		 * 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			graph = new int[h][w];
			visited = new boolean[h][w];
			
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				graph[y][x] = 1;
			}
			
			int cnt = 0;
			
			for(int r=0; r<h; r++) {
				for(int c=0; c<w; c++) {
					if (graph[r][c]==1 && !visited[r][c]) {
						dfs(r, c);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
			
		}

	}
	
	// dfs 메소드
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			if (nr>=0 && nr<h && nc>=0 && nc<w && !visited[nr][nc] && graph[nr][nc]==1) {
				dfs(nr, nc);
			}
		}
	}

}
