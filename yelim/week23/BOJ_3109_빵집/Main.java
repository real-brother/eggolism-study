package WEEK_23;

import java.util.Scanner;

public class Main_3109_빵집 {
	
	static int r, c;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1};	// 위부터
	static int[] dc = {1, 1, 1};
	static boolean check;
	static int cnt;
	
	public static void main(String[] args) {
		/* 문제) 3109_빵집
		 * 
		 * 가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다. 
		 * 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있다.
		 * 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하기
		 */

		Scanner sc = new Scanner(System.in);
		
		r = sc.nextInt();
		c = sc.nextInt();
		
		map = new int[r][c];
		visited = new boolean[r][c];
		
		for(int i=0; i<r; i++) {
			String s = sc.next();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 첫번째 열에서 각 행마다 dfs 탐색
		for(int i=0; i<r; i++) {
			visited[i][0] = true;
			check = false;
			dfs(i, 0);
		}
		
		System.out.println(cnt);
		
	}
	
	static void dfs(int x, int y) {
		// 마지막행에 왔으면 끝
		if (y == c-1) {
			check = true;	// 최적 경로 찾았음을 표시
			cnt++;	// 정답 개수 1 더해주기
			return;
		}
		
		// 탐색
		for(int d=0; d<3; d++) {
			int nx = x+dr[d];
			int ny = y+dc[d];
			
			// 범위 내에 있고
			if (nx>=0 && nx<r && ny>=0 && ny<c) {
				// 건물이 아니고, 이미 방문한 곳이 아니고, 최적 경로를 찾지 못했으면
				if (map[nx][ny]!='x' && !visited[nx][ny] && !check) {
					visited[nx][ny] = true;	// 방문체크
					dfs(nx, ny);	// 재귀 탐색
				}
			}
		}
	}

}
