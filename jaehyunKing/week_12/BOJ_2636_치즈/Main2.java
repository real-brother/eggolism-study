package BOJ_2636_치즈;

/*
 *  bfs + dfs로 풀었다
 *  bfs 한 번에 해결하고 싶어서 하다보니 뭔가 복잡하게 구현했는데 
 *  그냥 전에 빙산처럼 계속 확인하는 방식이 
 *  더 단순하게 풀 수 있을 거 같다.(한 시간마다 완탐)
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {
	static int N, M, time, cheeseCount;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static boolean[][] visited;
	static Queue<int[]> queue;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		
		bfs(0, 0);
		check();
		
		System.out.println(time);
		System.out.println(cheeseCount);
	}
	
	static void bfs(int startY, int startX) {
		queue.offer(new int[] {startY, startX, 0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int moveY = now[0] + dr[i];
				int moveX = now[1] + dc[i];
				
				if(0 > moveY || moveY >= N || moveX < 0 || moveX >= M) continue;
				
				// 다음 갈 곳이 아직 방문하지 않은 0이라면
				// dfs를 통해 인접한 0들을 queue에 넣는다
				// 예시만봐도 0의 개수가 많아서 now[2](시간)이 빠른 0이 늦게 나오는 문제 발생해서 사용
				// 이렇게 하면 예시에서 맨 바깥에 있는 0이 다 queue에 들어간다
				if(graph[moveY][moveX] == 0 && !visited[moveY][moveX]) {
					dfs(moveY, moveX, now[2]);
				}
				
				// 방문하지 않았다면 큐에 넣어주고(0은 이미 위에서 다 방문처리됨)
				if(!visited[moveY][moveX]) {
					visited[moveY][moveX] = true;
					graph[moveY][moveX] = now[2] + 1;
					queue.offer(new int[] {moveY, moveX, now[2] + 1});
				}
				
				// 방문하였더라도 기존 시간이 더 크면 바꿔준다 
				else if(graph[moveY][moveX] > now[2] + 1) {
					graph[moveY][moveX] = now[2] + 1;
					queue.offer(new int[] {moveY, moveX, now[2] + 1});
				}
			}
		}
	}
	
	// 인접한 0을 다 방문처리 + queue에 넣어주는 dfs 
	static void dfs(int nowY, int nowX, int count) {
		visited[nowY][nowX] = true;
		queue.offer(new int[] {nowY, nowX, count});
		for(int i = 0; i < 4; i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + dc[i];
			if(0 > moveY || moveY >= N || moveX < 0 || moveX >= M) continue;
			if(!visited[moveY][moveX] && graph[moveY][moveX] == 0) dfs(moveY, moveX, count);
		}
	}
	
	// 배열에서 가장 큰 숫자를 time에 저장하고, 가장 큰 숫자의 개수를 cheescCount로 카운팅
	static void check() {
		time = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(graph[r][c] > time) {
					time = graph[r][c];
					cheeseCount = 1;
				}
				else if(graph[r][c] == time) cheeseCount++;
			}
		}
	}
}