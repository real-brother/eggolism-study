package BOJ_13565_침투;

import java.util.Scanner;

public class Main {
	static boolean visited[][];
	static int board[][];
	static int N, M;
	static boolean result;

	static int[] dr = {1, 0, 0, -1}; //하좌우상
	static int[] dc = {0, -1, 1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		board = new int[N][M];
		visited = new boolean[N][M];
		
		result = false;
		
		for(int r = 0; r < N; r++) {
			String S = sc.next();
			for(int c = 0; c < M; c++) board[r][c] = S.charAt(c)-'0';
		}

		for(int i = 0; i < M; i++) {
			if(board[0][i] == 0) {
				dfs(0, i);
				if(result) break;
			}
		}
		
		if(result)System.out.println("YES");
		else System.out.println("NO");

	}
	
	static void dfs(int now_y, int now_x) {
		visited[now_y][now_x] = true;
		
		for(int i = 0; i < 4; i++) {
			int move_y = now_y + dr[i];
			int move_x = now_x + dc[i];
			if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M) {
				if(!visited[move_y][move_x] && board[move_y][move_x] == 0) {
					if(move_y == N-1) result = true;
					dfs(move_y, move_x);
				}
			}
		}
		
	}
}
