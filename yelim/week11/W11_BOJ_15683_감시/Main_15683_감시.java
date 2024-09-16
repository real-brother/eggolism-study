package WEEK_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	
	static class CCTV {
		int r;
		int c;
		
		public CCTV(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int n, m;
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};	// 우 상 좌 하
	static int[] dc = {1, 0, -1, 0};
	static List<CCTV> c;
	static int cctvCnt;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		/* 문제) 감시
		 * 사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, 
		 * CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
		 * 
		 * 지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호
		 * CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 
		 * 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		c = new ArrayList<>();
		cctvCnt = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]>=1 && map[i][j]<6) {
					c.add(new CCTV(i, j));
					cctvCnt++;
				}
			}
		}
		
		dfs(0);
		
		System.out.println(min);
		
	}

	// 범위 내에 있고 벽이 아닐 동안 탐색
	static void search(int r, int c, int d) {
		d %= 4;
		while(true) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			r = nr;
			c = nc;
			
			if (r<0 || r>=n || c<0 || c>=m) return;
			if (map[r][c] == 6) return;
			if (map[r][c] != 0) continue;
			map[r][c] = 9;
		}
	}
	
	
	
	
	static void dfs(int idx) {
		
		// 모든 cctv 다 봤으면 0인 값 개수 세주고 최소값 갱신
		if (idx == cctvCnt) {
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if (map[i][j] == 0) {
						cnt++;
					}
				}
			}
			
			min = Math.min(min, cnt);
			return;
		}
		
		int x = c.get(idx).r;
		int y = c.get(idx).c;
		
		int[][] tmp = new int[8][8];
		
		for(int d=0; d<4; d++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					tmp[i][j] = map[i][j];
					
				}
			}
			
			if (tmp[x][y] == 1) {
				search(x, y, d);
			}
			
			else if (tmp[x][y] == 2) {
				search(x, y, d);
				search(x, y, d+2);
			}
			
			else if (tmp[x][y] == 3) {
				search(x, y, d);
				search(x, y, d+1);
			}
			
			else if (tmp[x][y] == 4) {
				search(x, y, d);
				search(x, y, d+1);
				search(x, y, d+2);
			}
			
			else if (tmp[x][y] == 5) {
				search(x, y, d);
				search(x, y, d+1);
				search(x, y, d+2);
				search(x, y, d+3);
			}
			
			dfs(idx+1);
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					map[i][j] = tmp[i][j];
				}
			}
			
		}
		
	}

}
