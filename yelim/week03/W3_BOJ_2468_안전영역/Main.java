package W3_BOJ_2468_안전영역;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		/* 문제) 안전 영역
		 * 어떤 지역의 높이 정보가 주어졌을 때, 
		 * 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하는 프로그램을 작성하시오.
		 * 
		 * 첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. 
		 * N은 2 이상 100 이하의 정수이다. 
		 * 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다. 
		 * 각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][n];
		
		int max = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		
		int answer = 0;
		
		// max값 미만일 때부터 물에 잠기는 부분 검사
		// arr 배열 탐색하면서 m 이하일 때 graph에 1 넣어주고 dfs
		for(int m=0; m<max; m++) {
			// 초기화
			graph = new int[n][n];
			visited = new boolean[n][n];
			int cnt = 0;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if (arr[i][j]<=m) {
						graph[i][j] = 1;
					}
				}
			}	// 그래프에 1 넣기 완료
			
			// graph 돌면서 dfs 호출하고 cnt 세기
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if (graph[i][j]==0 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			
			if (cnt > answer) {
				answer = cnt;
			}
		}
		
		System.out.println(answer);
		
	}
	
	
	// dfs
	public static void dfs(int x, int y) {
		
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<n && !visited[nr][nc] && graph[nr][nc]==0) {
				dfs(nr, nc);
			}
		}
	}

}
