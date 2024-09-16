package BOJ_10026_적록색약;

import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		char[][] picture = new char[N][N];
		char[][] weak_picture = new char[N][N];
		boolean[][] visited = new boolean[N][N];
		boolean[][] weak_visited = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			String S = sc.next();
			for(int c = 0; c < N; c++) {
				char color = S.charAt(c);
				if(color == 'R' | color == 'G') {
					weak_picture[r][c] = 'G';
					if(color == 'R') picture[r][c] = 'R';
					else picture[r][c] = 'G';
				}
				else {
					weak_picture[r][c] = 'B';
					picture[r][c] = 'B';
				}
			}
		}
		int count = 0;
		int weak_count = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!visited[r][c]) {
					dfs(r, c, picture, visited);
					count++;
				}
				if(!weak_visited[r][c]) {
					dfs(r, c, weak_picture, weak_visited);
					weak_count++;
				}
			}
		}
		
		System.out.print(count+ " " + weak_count);

	}
	
	static void dfs(int now_y, int now_x, char[][] picture, boolean[][] visited) {
		
		visited[now_y][now_x] = true;
		
		for(int i = 0; i < 4; i++) {
			int move_y = now_y + dr[i];
			int move_x = now_x + dc[i];
				
			if(0 <= move_y && move_y < picture.length && 0 <= move_x && move_x < picture[0].length) {
				if(!visited[move_y][move_x] && (picture[move_y][move_x] == picture[now_y][now_x])) {
					dfs(move_y, move_x, picture, visited);
				}
			}
		}
	}
}


