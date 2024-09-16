package BOJ_2468_안전영역;

import java.util.Scanner;

public class Main {
	static int[][] new_ground;
	static boolean[][] visited;
	static int N;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int[][] ground = new int[N][N];
		int max_area_count = Integer.MIN_VALUE;
		int max_height = Integer.MIN_VALUE;
		int min_height = Integer.MAX_VALUE;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				ground[r][c] = sc.nextInt();
				if(max_height < ground[r][c]) max_height = ground[r][c];
				if(min_height > ground[r][c]) min_height = ground[r][c];				
			}
		}
		
		for(int height = min_height-1; height <= max_height; height++) {
			new_ground = new int[N][N];
			visited = new boolean[N][N];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(ground[r][c] <= height) new_ground[r][c] = 0;
					else new_ground[r][c] = ground[r][c];
				}
			}
			
			int area_count = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(new_ground[r][c] != 0 && !visited[r][c]) {
						area_count++;
						dfs(r, c);
					}
				}
			}
			if(max_area_count < area_count) max_area_count = area_count;
			
		}
		System.out.println(max_area_count);
		
	}
	
	static void dfs(int now_y, int now_x) {
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		visited[now_y][now_x] = true;
		
		for(int i = 0; i < 4; i++) {
			int move_y = now_y + dr[i];
			int move_x = now_x + dc[i];
			if(0 <= move_y && move_y < N && 0 <= move_x && move_x < N) {
				if(!visited[move_y][move_x] && new_ground[move_y][move_x] != 0) dfs(move_y, move_x);
			}
		}
	}

}
