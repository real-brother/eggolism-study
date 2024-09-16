package BOJ_14503_로봇청소기;

import java.util.Scanner;

public class Main {
	static int N, M, count;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌 
	static int[] dc = {0, 1, 0, -1}; // 문제에서 시키는대로

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int robotY = sc.nextInt();
		int robotX = sc.nextInt();
		int dir = sc.nextInt();
		
		graph = new int[N][M];
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		count = 0;
		visited = new boolean[N][M];
		dfs(robotY, robotX, dir);
		
		System.out.println(count);
	}
	
	// 1 -> 벽, 0 -> 청소해야함
	static void dfs(int nowY, int nowX, int dir) {
		
		// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		if(graph[nowY][nowX] == 0 && !visited[nowY][nowX]) {
			visited[nowY][nowX] = true;
			count++;
		}
		
		boolean noBlank = true;
		
		// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
		for(int i = 0; i < 4 ;i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + dc[i];
			if(0 <= moveY && moveY < N && 0 <= moveX && moveX < M) {
				if(graph[moveY][moveX] == 0 && !visited[moveY][moveX]) {
					
					//반시계 방향으로 90도 회전한다.
					int next = (dir + 3) % 4;
					
					// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
					// 1번으로 돌아간다.(dfs)
					if(graph[nowY + dr[next]][nowX + dc[next]] == 0 && !visited[nowY + dr[next]][nowX + dc[next]]) {
							dfs(nowY+dr[next], nowX+dc[next], next);
					}
					else dfs(nowY, nowX, next);
					noBlank = false;
					break;
				}
			}
		}
		// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
		// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
		if(noBlank) {
			if(graph[nowY - dr[dir]][nowX - dc[dir]] == 0) {
				dfs(nowY - dr[dir], nowX - dc[dir], dir);
			}
			// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
			else return;
		}
	}

}