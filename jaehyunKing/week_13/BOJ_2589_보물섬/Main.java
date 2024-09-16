package BOJ_2589_보물섬;

/*
 *  그냥 L 중에서 최단거리로 갔을 때 가장 멀리 떨어져 있는 2개 사이의 거리를 구하는 문제
 *  각 L들을 대상으로 bfs를 돌아서 
 *  가장 큰 count값을 답으로 출력 
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, max;
	static char[][] graph;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		graph = new char[N][M];
		
		for(int r = 0; r < N; r++) {
			String S = sc.next();
			for(int c = 0; c < M; c++) {
				graph[r][c] = S.charAt(c);
			}
		}
		
		max = 0;
		// L을 찾으면 bfs를 실행
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(graph[r][c] == 'L') {
					bfs(r, c);
				}
			}
		}
		
		System.out.println(max);
	}
	
	
	// 기본적인 bfs
	static void bfs(int startY, int startX) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[startY][startX] = true;
		queue.offer(new int[] {startY, startX, 0});
		int count = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			// 현재 count를 갱신해준다.
			count = now[2];
			
			for(int i = 0; i < 4; i++) {
				int moveY = now[0] + dr[i]; 
				int moveX = now[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < M) {
					if(!visited[moveY][moveX] && graph[moveY][moveX] == 'L') {
						visited[moveY][moveX] = true;
						queue.offer(new int[] {moveY, moveX, now[2] + 1});
					}
				}
			}
		}
		// max값보다 count값이 크다면 갱신
		if(max < count) max = count; 
	}

}