package W3_BOJ_2583_영역구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int width = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());	// 직사각형 개수
		
		graph = new int[n][m];
		visited = new boolean[n][m];
		
		// 배열에 입력받은 영역 안에 1 넣어주기
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int lx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			
			for(int r=0; r<(ry-ly); r++) {
				for(int c=0; c<(rx-lx); c++) {
					graph[r+ly][c+lx] = 1;
				}
			}
		}

		
		int cnt = 0;
		List<Integer> wList = new ArrayList<>();	// 넓이 저장할 리스트
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				// 영역이 아니고, 방문한 적이 없으면 dfs 호출
				if (graph[i][j]==0 && !visited[i][j]) {
					dfs(i, j);
					cnt++;	// 영역 개수 1 증가
					wList.add(width);	// 넓이를 리스트에 저장
					width = 1;	// 넓이 1로 초기화
				}
			}
		}
		
		Collections.sort(wList);	// 넓이 오름차순 정렬
		
		System.out.println(cnt);	// 영역 개수
		for(int x : wList) {
			System.out.print(x+" ");
		}
	}
	
	// dfs 메소드
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && graph[nr][nc]==0) {
				width++;	// 넓이 1 증가
				dfs(nr, nc);
			}
		}
	}

}
