package W3_BOJ_10026_적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static char[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/* 문제) 적록색약
		 * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 
		 * 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 
		 * 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. 
		 * (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
		 * 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
		 */
		
		// 적록색약 아닌 사람까지는 뽑아줌. 적록색약인 사람꺼는 아직 못함 ㅠ
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		graph = new char[n][n];
		visited = new boolean[n][n];
		
		// graph에 값 넣어주기
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		int rcnt = 1;
		int bcnt = 1;
		int gcnt = 1;
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if (graph[i][j] == 'R' && !visited[i][j]) {
					dfs(i, j);
					rcnt++;
				}
				else if (graph[i][j] == 'G' && !visited[i][j]) {
					dfs(i, j);
					gcnt++;
				}
				else if (graph[i][j] == 'B' && !visited[i][j]) {
					dfs(i, j);
					bcnt++;
				}
			}
		}
		
		int rgb = rcnt+gcnt+bcnt;
		
		System.out.println(rgb);
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<n && !visited[nr][nc]) {
				if (graph[nr][nc] == 'R') {
					dfs(nr, nc);
				}
				if (graph[nr][nc] == 'G') {
					dfs(nr, nc);
				}
				if (graph[nr][nc] == 'B') {
					dfs(nr, nc);
				}
			}
		}
	}

}
