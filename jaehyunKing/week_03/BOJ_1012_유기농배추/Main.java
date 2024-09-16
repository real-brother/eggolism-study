package BOJ_1012_유기농배추;

import java.util.Scanner;

public class Main {
	static int[][] ground;
	static boolean[][] visited;
	static int N, M;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			
			M = sc.nextInt();
			N = sc.nextInt();
			int K = sc.nextInt();
			
			ground = new int[N][M];
			visited = new boolean[N][M];
			
			for(int j = 0; j < K; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				ground[y][x] = 1;
			}
			
			int count = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(ground[r][c] == 1 && !visited[r][c]) {
						count++;
						dfs(r, c);
					}
				}
			}
			System.out.println(count);
		}

	}
	
	static void dfs(int now_y, int now_x) {
		visited[now_y][now_x] = true;
		
		for(int i = 0; i < 4; i++) {
			int move_y = now_y + dr[i];
			int move_x = now_x + dc[i];
			if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M ) {
				if(!visited[move_y][move_x] && ground[move_y][move_x] == 1) dfs(move_y, move_x);
			}
		}
	}

}
