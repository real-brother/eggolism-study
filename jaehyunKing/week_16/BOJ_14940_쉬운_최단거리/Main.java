package BOJ_14940_쉬운_최단거리;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 처음 1인 애들을 다 -1로 바꿔준다.
// 역으로 도착 지점에서 bfs

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static int n, m;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		graph = new int[n][m];
		
		int startY = 0;
		int startX = 0;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				int temp = sc.nextInt();
				if(temp == 2) {
					startY = r;
					startX = c;
				}
				else if(temp == 1) {
					graph[r][c] = -1;
				}
			}
		}
		
		bfs(startY, startX);
		
		// sb를 안 썼더니 시간 초과가 남..
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				sb.append(graph[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 평범한 bfs
	// visited 대신 -1일 때만 이동가능하게 함
	static void bfs(int nowY, int nowX) {
		Queue<int[]> queue = new LinkedList<>();
		graph[nowY][nowX] = 0;
		queue.offer(new int[] {nowY, nowX, 0});
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				
				if(0 <= moveY && moveY < n && 0 <= moveX && moveX < m) {
					if(graph[moveY][moveX] == -1) {
						graph[moveY][moveX] = num[2] + 1;
						queue.offer(new int[] {moveY, moveX, num[2] + 1});
					}
				}
			}
		}
	}
}