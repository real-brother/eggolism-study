package BOJ_17144_미세먼지안녕;

/*
 *  문제에서 시키는대로 구현
 *  주형이가 좋아하는 동시성(?) 문제를 해결하기 위해
 *  new_graph에 계산해야할 값을 적어두고 마지막에 더 해줌
 */
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		int C = sc.nextInt();
		int T = sc.nextInt();
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int[][] graph = new int[R][C];
		int[][] new_graph = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		
		int air_y = 0;
		
		// 공기청정기 자리 찾기
		for(int r = 0 ; r < R; r++) {
			for(int c = 0; c < C; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == -1) air_y = Math.max(r, air_y);
				else if(graph[r][c] != 0) visited[r][c] = true;
			}
		}
		
		// T초
		for(int time = 0; time < T; time++) {
			
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					
					// 원래 먼지가 있던 자리
					if(visited[r][c]) {
						
						// 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
						for(int i = 0; i < 4; i++) {
							int move_y = r + dr[i];
							int move_x = c + dc[i];
							int score = graph[r][c] / 5;
							if(0 <= move_y && move_y < R && 0 <= move_x && move_x < C && graph[move_y][move_x] != -1) {
								new_graph[move_y][move_x] += score;
								new_graph[r][c] -= score;
							}
						}
					}
				}
			}
			
			// 미세먼지 확산한 거를 합쳐줌
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(graph[r][c] != 0 && graph[r][c] != -1) visited[r][c] = true;
					else visited[r][c] = false;
					graph[r][c] += new_graph[r][c];
					new_graph[r][c] = 0;
				}
			}
			
			int before = 0;
			int before2 = 0;
			int tmp = 0; 
			int tmp2 = 0;
			
			// r -> air_y, air_y - 1
			// c -> air_x
			
			// 2. 공기청정기가 작동한다.
			// 오른쪽으로 1칸 이동
			for(int c = 1; c < C; c++) {
				tmp = graph[air_y][c];
				if(c == 1) graph[air_y][c] = 0;
				else graph[air_y][c] = before;
				before = tmp;
				
				tmp2 = graph[air_y-1][c];
				if(c == 1) graph[air_y-1][c] = 0;
				else graph[air_y-1][c] = before2;
				before2 = tmp2;
			}
			
			
			//시계 먼지(아래로 1칸 이동)
			for(int r = air_y+1; r < R; r++) {
				tmp = graph[r][C-1];
				graph[r][C-1] = before;
				before = tmp;
			}
			
			//반시계 먼지(위로 1칸 이동)
			for(int r = air_y-2; r >= 0; r--) {
				tmp2 = graph[r][C-1];
				graph[r][C-1] = before2;
				before2 = tmp2;
			}
			
			// 왼쪽으로 이동
			for(int c = C-2; c >= 0; c--) {
				tmp = graph[R-1][c];
				graph[R-1][c] = before;
				before = tmp;
				
				tmp2 = graph[0][c];
				graph[0][c] = before2;
				before2 = tmp2;
			}
			
			
			//시계 먼지(위로 이동)
			for(int r = R-2; r >= air_y; r--) {
				tmp = graph[r][0];
				if(r != air_y) graph[r][0] = before;
				before = tmp;
			}
			
			//반시계 먼지(아래로 이동)
			for(int r = 1; r <= air_y-1; r++) {
				tmp2 = graph[r][0];
				if(r != air_y-1) graph[r][0] = before2;
				before2 = tmp2;
			}
			
			// 미세먼지 위치 
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(graph[r][c] != 0 && graph[r][c] != -1) visited[r][c] = true;
					else visited[r][c] = false;
				}
			}
		}
	
		int sum = 0;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(graph[r][c] != 0 && graph[r][c] != -1) sum += graph[r][c];
			}
		}
		
		System.out.println(sum);

	}

}